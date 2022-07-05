package com.example.springvotingSystem.Repository;

import com.example.springvotingSystem.Entity.Candidates;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidatesRepository extends JpaRepository<Candidates,Long> {
}
