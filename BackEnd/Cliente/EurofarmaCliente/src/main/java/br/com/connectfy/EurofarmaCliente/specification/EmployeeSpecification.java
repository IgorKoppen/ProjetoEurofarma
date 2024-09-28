package br.com.connectfy.EurofarmaCliente.specification;

import br.com.connectfy.EurofarmaCliente.models.*;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

public class EmployeeSpecification implements Specification<Employee> {

    private SearchCriteria criteria;

    public EmployeeSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }


    @Override
    public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        switch (criteria.getOperation()) {
            case ":":
                if (root.get(criteria.getKey()).getJavaType() == String.class) {
                    return builder.like(builder.lower(root.get(criteria.getKey())), "%" + criteria.getValue().toString().toLowerCase() + "%");
                } else {
                    return builder.equal(root.get(criteria.getKey()), criteria.getValue());
                }
            case "=":
                switch (criteria.getKey()) {
                    case "roleId":
                    case "roleName":
                    case "departmentId":
                    case "departmentName":
                        Join<Employee, Role> roleJoin = root.join("role", JoinType.INNER);
                        if (criteria.getKey().equals("roleId")) {
                            return builder.equal(roleJoin.get("id"), criteria.getValue());
                        } else if (criteria.getKey().equals("roleName")) {
                            return builder.like(builder.lower(roleJoin.get("roleName")), "%" + criteria.getValue().toString().toLowerCase() + "%");
                        } else {
                            Join<Role, Department> departmentJoin = roleJoin.join("department", JoinType.INNER);
                            if (criteria.getKey().equals("departmentId")) {
                                return builder.equal(departmentJoin.get("id"), criteria.getValue());
                            } else if (criteria.getKey().equals("departmentName")) {
                                return builder.like(builder.lower(departmentJoin.get("departName")), "%" + criteria.getValue().toString().toLowerCase() + "%");
                            }
                        }
                    case "permissionId":
                    case "permissionDescription":
                        Join<Employee, Permission> permissionJoin = root.join("permissions", JoinType.INNER);
                        if (criteria.getKey().equals("permissionId")) {
                            return builder.equal(permissionJoin.get("id"), criteria.getValue());
                        } else if (criteria.getKey().equals("permissionDescription")) {
                            return builder.like(builder.lower(permissionJoin.get("description")), "%" + criteria.getValue().toString().toLowerCase() + "%");
                        }
                    default:
                        return null;
                }
            default:
                return null;
        }
    }
}
