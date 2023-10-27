package com.teka.services.secret_variables


interface SecretVariables {

    fun get(name: SecretVariableName) : String?

    fun get(name: SecretVariableName, defaultValue: String) : String?

    fun require(name: SecretVariableName) : String
}