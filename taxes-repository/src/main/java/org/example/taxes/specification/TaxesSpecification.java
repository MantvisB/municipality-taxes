package org.example.taxes.specification;

import org.example.taxes.model.Tax;
import org.example.taxes.search.TaxesSearch;
import org.springframework.data.jpa.domain.Specification;

public class TaxesSpecification {

    public static Specification<Tax> build(TaxesSearch search) {

        Specification<Tax> specification = Specification.where(null);

        if(search.getMunicipalityId() != null) {

            specification = specification.and(((root, query, cb) -> cb.equal(root.join("municipality"), search.getMunicipalityId())));
        }

        if(search.getDate() != null) {
            specification = specification.and(((root, query, cb) -> cb.and(
                    cb.lessThanOrEqualTo(root.get("dateFrom"), search.getDate()),
                    cb.greaterThanOrEqualTo(root.get("dateTo"), search.getDate()))));
        }

        return specification;
    }


}
