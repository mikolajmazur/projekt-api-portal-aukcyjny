package pl.edu.wat.wcy.isi.ai.PortalAukcyjny.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    public Role getRoleByRoleName(String roleName);
}
