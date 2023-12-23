package com.example.isusevicemaven.dto;

import entity.FormOfEducation;

public record ChangeEduFormRequest(
        Integer id,
        FormOfEducation formOfEducation
) {
}
