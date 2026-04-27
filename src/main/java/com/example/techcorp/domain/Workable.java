package com.example.techcorp.domain;

/**
 * Any entity that can contribute work units to a project must implement this contract.
 */
public interface Workable {
    int work();
}