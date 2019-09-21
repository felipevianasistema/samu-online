package br.com.samuonline.dao;

import java.util.List;

public interface DataAccessObject<T> {

    public void adicionar(T obj) throws Exception;

    public void excluir(T obj) throws Exception;

    public void atualizar(T obj) throws Exception;

    public List<T> consultar() throws Exception;

    public List<T> filtrarPesquisa(T obj) throws Exception;
}
