package com.curso.spring.api.response;

import java.util.ArrayList;
import java.util.List;

public class Response<T> //Con la T decimos que la clase se puede mutar
{
    private T resultado;
    private List<String> errores; //Son facades
    public Response()
    {

    }

    public T getResultado()
    {
        return resultado;
    }

    public List<String> getErrores()
    {
        if(errores==null)
        {
            this.errores= new ArrayList<>();
        }
        return errores;
    }

    public void setResultado(T resultado)
    {
        this.resultado=resultado;
    }

    public void setErrores(List<String> errores)
    {
        this.errores=errores;
    }

}