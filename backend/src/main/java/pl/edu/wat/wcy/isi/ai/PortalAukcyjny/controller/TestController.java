package pl.edu.wat.wcy.isi.ai.PortalAukcyjny.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.DTO.AuctionMiniDTO;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.Auction;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.Category;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.Role;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.User;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.event.AuctionEndedEvent;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.repository.AuctionRepository;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.repository.RoleRepository;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.repository.UserRepository;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.service.CategoryService;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.service.EmailService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/test")
public class TestController {
    @Autowired
    private AuctionRepository repo;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private UserRepository userRepo;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @GetMapping("/auth")
    @PreAuthorize("hasRole('STANDARD_USER')")
    public String onlyAuthenticated(Principal user) {
        return user.getName();
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String onlyAdmin(){
        return "admin";
    }
}
