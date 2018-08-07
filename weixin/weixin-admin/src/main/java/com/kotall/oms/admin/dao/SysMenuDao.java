package com.kotall.oms.admin.dao;

import com.kotall.oms.admin.entity.SysMenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author zpwang
 * @version 1.0.0
 */
public interface SysMenuDao extends JpaRepository<SysMenuEntity, Integer> {

    @Query(" from SysMenuEntity where pid =:pid")
    List<SysMenuEntity> findByPid(@Param("pid") int pid);
}
