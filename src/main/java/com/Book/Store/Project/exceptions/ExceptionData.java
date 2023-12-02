package com.Book.Store.Project.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Objects;

public record ExceptionData(String code, String msg) {}
