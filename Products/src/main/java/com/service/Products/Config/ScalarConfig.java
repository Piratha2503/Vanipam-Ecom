package com.service.Products.Config;

import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import org.springframework.context.annotation.Bean;

public class ScalarConfig {

    @Bean
    public GraphQLScalarType longScalar() {
        return ExtendedScalars.GraphQLLong;
    }

    @Bean
    public GraphQLScalarType bigDecimalScalar() {
        return ExtendedScalars.GraphQLBigDecimal;
    }

    @Bean
    public GraphQLScalarType dateTimeScalar() {
        return ExtendedScalars.DateTime;
    }
}
