package com.agendafacil.agendafacil_api.adapters.out.repository.profissional;

import com.agendafacil.agendafacil_api.application.ports.out.profissional.DeletarProfissionalOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DeletarProfissionalAdapter implements DeletarProfissionalOutputPort {

    private final ProfissionalRepository profissionalRepository;

    @Override
    public void deletar(Long id) {
        profissionalRepository.deleteById(id);
    }
}
