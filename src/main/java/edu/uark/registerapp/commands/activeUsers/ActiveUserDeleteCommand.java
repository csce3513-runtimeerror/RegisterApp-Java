package edu.uark.registerapp.commands.activeUsers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.uark.registerapp.commands.VoidCommandInterface;
import edu.uark.registerapp.commands.exceptions.NotFoundException;
import edu.uark.registerapp.models.entities.ActiveUserEntity;
import edu.uark.registerapp.models.repositories.ActiveUserRepository;

@Service
public class ActiveUserDeleteCommand implements VoidCommandInterface {

    @Transactional
    @Override
    public void execute() {
        final Optional<ActiveUserEntity> entity =
                this.activeuserRepository.findBySessionKey(this.sessionKey);

        if (!entity.isPresent()) { 
            throw new NotFoundException("ActiveUser");
        }        
        this.activeuserRepository.delete(entity.get());
    }
    //properties
    private String sessionKey;

    @Autowired
    private ActiveUserRepository activeuserRepository;
}