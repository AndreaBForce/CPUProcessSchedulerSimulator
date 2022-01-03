package service;

import service.process.Process;

import java.util.List;

public interface IAlgorithm {
    List<Process> compute(List<? extends Process> processes);
}