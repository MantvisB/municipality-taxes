package org.example.taxes.specification;

import org.example.taxes.model.Municipality;
import org.example.taxes.search.MunicipalitySearch;
import org.springframework.data.jpa.domain.Specification;

public class MunicipalitySpecification {

    public static Specification<Municipality> build(MunicipalitySearch search) {

        Specification<Municipality> specification = Specification.where(null);

        if(search.getName() != null) {

            specification = specification.and(((root, query, cb) -> cb.equal(root.get("name"), search.getName())));
        }

        return specification;
    }
}
