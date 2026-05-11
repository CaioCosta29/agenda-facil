package com.agendafacil.agendafacil_api.adapters.out.repository.profissional;

import com.agendafacil.agendafacil_api.adapters.out.repository.profissional.mapper.ProfissionalEntityMapper;
import com.agendafacil.agendafacil_api.application.core.domain.Profissional;
import com.agendafacil.agendafacil_api.application.ports.out.profissional.ColetarProfissionaisOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ColetarProfissionaisAdapter implements ColetarProfissionaisOutputPort {

    private final ProfissionalRepository profissionalRepository;
    private final ProfissionalEntityMapper profissionalEntityMapper;

    @Override
    public List<Profissional> coletar() {
        var profissionaisEntity = profissionalRepository.findAll();

        return profissionaisEntity.stream().map(profissionalEntityMapper::toDomain).toList();
    }
}
