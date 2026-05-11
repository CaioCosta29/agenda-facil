package com.agendafacil.agendafacil_api.adapters.out.repository.profissional;

import com.agendafacil.agendafacil_api.adapters.out.repository.profissional.mapper.ProfissionalEntityMapper;
import com.agendafacil.agendafacil_api.application.core.domain.Profissional;
import com.agendafacil.agendafacil_api.application.ports.out.profissional.CadastrarProfissionalOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CadastrarProfissionalAdapter implements CadastrarProfissionalOutputPort {

    private final ProfissionalRepository profissionalRepository;
    private final ProfissionalEntityMapper profissionalEntityMapper;

    @Override
    public Profissional cadastrar(Profissional profissional) {
        var profissionalEntity = profissionalEntityMapper.toEntity(profissional);
        var profissionalSalvo = profissionalRepository.save(profissionalEntity);

        return profissionalEntityMapper.toDomain(profissionalSalvo);
    }
}
