package br.com.connectfy.EurofarmaCliente.specification;

import br.com.connectfy.EurofarmaCliente.models.*;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;



public class TrainingSpecification implements Specification<Training> {
    private SearchCriteria criteria;

    public TrainingSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Training> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        switch (criteria.getOperation()) {
            case ":":
                if (root.get(criteria.getKey()).getJavaType() == String.class) {
                    return builder.like(builder.lower(root.get(criteria.getKey())), "%" + criteria.getValue().toString().toLowerCase() + "%");
                } else {
                    return builder.equal(root.get(criteria.getKey()), criteria.getValue());
                }
            case "=":
                switch (criteria.getKey()) {
                    case "departments.id" ->{
                        Join<Training, Department> departmentJoin = root.join("departments", JoinType.INNER);
                        return builder.equal(departmentJoin.get("id"),  criteria.getValue());
                    }
                    case "tags.id" ->{
                        Join<Training, Tag> tagJoin = root.join("tags", JoinType.INNER);
                        return builder.equal(tagJoin.get("id"),  criteria.getValue());
                    }
                    case "tagName" -> {
                        Join<Training, Tag> tagJoin = root.join("tags", JoinType.INNER);
                        return builder.like(builder.lower(tagJoin.get("name")), "%" + criteria.getValue().toString().toLowerCase() + "%");
                    }
                    case "departmentName" -> {
                        Join<Training, Department> departmentJoin = root.join("departments", JoinType.INNER);
                        return builder.like(builder.lower(departmentJoin.get("departName")), "%" + criteria.getValue().toString().toLowerCase() + "%");
                    }
                    case "instructorRegistration" -> {
                        Join<Training, Instructor> instructorJoin = root.join("instructors", JoinType.INNER);
                        Join<Instructor, Employee> employeeJoin = instructorJoin.join("employee", JoinType.INNER);
                        return builder.equal(employeeJoin.get("employeeRegistration"), criteria.getValue());
                    }
                    case "employeeRegistration" -> {
                        Join<Training, EmployeeTraining> employeeTrainingJoin = root.join("employeeTrainings", JoinType.INNER);
                        Join<EmployeeTraining, Employee> employeeJoinEmployeeTraining = employeeTrainingJoin.join("employee", JoinType.INNER);
                        return builder.equal(employeeJoinEmployeeTraining.get("employeeRegistration"), criteria.getValue());
                    }
                }
            default:
                return null;
        }
    }
}




