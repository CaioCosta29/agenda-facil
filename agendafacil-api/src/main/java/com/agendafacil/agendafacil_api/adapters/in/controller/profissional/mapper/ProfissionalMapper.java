package com.agendafacil.agendafacil_api.adapters.in.controller.profissional.mapper;

import com.agendafacil.agendafacil_api.adapters.in.controller.profissional.request.DadosAtualizacaoProfissional;
import com.agendafacil.agendafacil_api.adapters.in.controller.profissional.request.DadosCadastroProfissional;
import com.agendafacil.agendafacil_api.adapters.in.controller.profissional.response.DadosDetalhamentoProfissional;
import com.agendafacil.agendafacil_api.application.core.domain.Profissional;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfissionalMapper {

    Profissional toDomain(DadosCadastroProfissional dadosCadastroProfissional);

    DadosDetalhamentoProfissional toDetalhamento(Profissional profissional);

    Profissional fromDadosAtualizaoToDomain(DadosAtualizacaoProfissional dadosAtualizacaoProfissional);
}
