package com.agendafacil.agendafacil_api.adapters.out.repository.profissional;

import com.agendafacil.agendafacil_api.adapters.out.repository.profissional.mapper.ProfissionalEntityMapper;
import com.agendafacil.agendafacil_api.application.core.domain.Profissional;
import com.agendafacil.agendafacil_api.application.core.exception.ProfissionalNaoEncontradoException;
import com.agendafacil.agendafacil_api.application.ports.out.profissional.ColetarProfissionalPeloIdOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ColetarProfissionalPeloIdAdapter implements ColetarProfissionalPeloIdOutputPort {

    private final ProfissionalRepository profissionalRepository;
    private final ProfissionalEntityMapper profissionalEntityMapper;

    @Override
    public Profissional coletar(Long id) {
        var profissionalEntity = profissionalRepository.findById(id).orElseThrow(() -> new ProfissionalNaoEncontradoException(id));

        return profissionalEntityMapper.toDomain(profissionalEntity);
    }
}
