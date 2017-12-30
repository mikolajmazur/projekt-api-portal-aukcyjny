package pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @OneToMany(
            mappedBy = "category",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    //@JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonIgnore
    private List<Auction> auctions = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Category parent;
    @OneToMany(
            mappedBy = "parent",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    //@JsonIgnore
    private List<Category> children = new ArrayList<>();

    @Builder
    public Category(String name) {
        this.name = name;
    }

    public void addChildCategory(Category child){
        children.add(child);
        child.setParent(this);
    }

    public void removeChildCategory(Category child){
        children.remove(child);
        child.setParent(null);
    }

    public void addAuction(Auction auction){
        auctions.add(auction);
        auction.setCategory(this);
    }

    public void removeAuction(Auction auction){
        auctions.remove(auction);
        auction.setCategory(null);
    }
}
