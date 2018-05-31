package org.zgl.jetty.operation;


public interface IOperationCommand extends Runnable{
    Object execute();
    default void broadcast(){}
}