package com.agendafacil.agendafacil_api.adapters.out.repository.profissional;

import com.agendafacil.agendafacil_api.adapters.out.repository.profissional.entity.ProfissionalEntity;
import com.agendafacil.agendafacil_api.adapters.out.repository.profissional.mapper.ProfissionalEntityMapper;
import com.agendafacil.agendafacil_api.application.core.domain.Profissional;
import com.agendafacil.agendafacil_api.application.core.exception.ProfissionalNaoEncontradoException;
import com.agendafacil.agendafacil_api.application.ports.out.profissional.AtualizarProfissionalOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AtualizarProfissionalAdapter implements AtualizarProfissionalOutputPort {

    private final ProfissionalEntityMapper profissionalEntityMapper;
    private final ProfissionalRepository profissionalRepository;

    @Override
    public Profissional atualizar(Long id, Profissional profissional) {
       var profissionalEntity = profissionalRepository.findById(id).orElseThrow(() -> new ProfissionalNaoEncontradoException(id));

       atualizarCampos(profissionalEntity, profissional);
       profissionalRepository.save(profissionalEntity);

       return profissionalEntityMapper.toDomain(profissionalEntity);
    }

    private void atualizarCampos(ProfissionalEntity entity, Profissional profissional) {
        if (profissional.getNome() != null) { entity.setNome(profissional.getNome()); }
        if (profissional.getEspecialidade() != null) { entity.setEspecialidade(profissional.getEspecialidade()); }
        if (profissional.getEmail() != null) { entity.setEmail(profissional.getEmail()); }
    }
}
