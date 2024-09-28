// ignore_for_file: unnecessary_getters_setters

import '/backend/schema/util/schema_util.dart';

import 'index.dart';
import '/flutter_flow/flutter_flow_util.dart';

class UserProfileStruct extends BaseStruct {
  UserProfileStruct({
    int? id,
    String? name,
    String? surname,
    String? cellphoneNumber,
    int? employeeRegistration,
    String? departmentName,
    String? roleName,
  })  : _id = id,
        _name = name,
        _surname = surname,
        _cellphoneNumber = cellphoneNumber,
        _employeeRegistration = employeeRegistration,
        _departmentName = departmentName,
        _roleName = roleName;

  // "id" field.
  int? _id;
  int get id => _id ?? 0;
  set id(int? val) => _id = val;

  void incrementId(int amount) => id = id + amount;

  bool hasId() => _id != null;

  // "name" field.
  String? _name;
  String get name => _name ?? '';
  set name(String? val) => _name = val;

  bool hasName() => _name != null;

  // "surname" field.
  String? _surname;
  String get surname => _surname ?? '';
  set surname(String? val) => _surname = val;

  bool hasSurname() => _surname != null;

  // "cellphoneNumber" field.
  String? _cellphoneNumber;
  String get cellphoneNumber => _cellphoneNumber ?? '';
  set cellphoneNumber(String? val) => _cellphoneNumber = val;

  bool hasCellphoneNumber() => _cellphoneNumber != null;

  // "employeeRegistration" field.
  int? _employeeRegistration;
  int get employeeRegistration => _employeeRegistration ?? 0;
  set employeeRegistration(int? val) => _employeeRegistration = val;

  void incrementEmployeeRegistration(int amount) =>
      employeeRegistration = employeeRegistration + amount;

  bool hasEmployeeRegistration() => _employeeRegistration != null;

  // "departmentName" field.
  String? _departmentName;
  String get departmentName => _departmentName ?? '';
  set departmentName(String? val) => _departmentName = val;

  bool hasDepartmentName() => _departmentName != null;

  // "roleName" field.
  String? _roleName;
  String get roleName => _roleName ?? '';
  set roleName(String? val) => _roleName = val;

  bool hasRoleName() => _roleName != null;

  static UserProfileStruct fromMap(Map<String, dynamic> data) =>
      UserProfileStruct(
        id: castToType<int>(data['id']),
        name: data['name'] as String?,
        surname: data['surname'] as String?,
        cellphoneNumber: data['cellphoneNumber'] as String?,
        employeeRegistration: castToType<int>(data['employeeRegistration']),
        departmentName: data['departmentName'] as String?,
        roleName: data['roleName'] as String?,
      );

  static UserProfileStruct? maybeFromMap(dynamic data) => data is Map
      ? UserProfileStruct.fromMap(data.cast<String, dynamic>())
      : null;

  Map<String, dynamic> toMap() => {
        'id': _id,
        'name': _name,
        'surname': _surname,
        'cellphoneNumber': _cellphoneNumber,
        'employeeRegistration': _employeeRegistration,
        'departmentName': _departmentName,
        'roleName': _roleName,
      }.withoutNulls;

  @override
  Map<String, dynamic> toSerializableMap() => {
        'id': serializeParam(
          _id,
          ParamType.int,
        ),
        'name': serializeParam(
          _name,
          ParamType.String,
        ),
        'surname': serializeParam(
          _surname,
          ParamType.String,
        ),
        'cellphoneNumber': serializeParam(
          _cellphoneNumber,
          ParamType.String,
        ),
        'employeeRegistration': serializeParam(
          _employeeRegistration,
          ParamType.int,
        ),
        'departmentName': serializeParam(
          _departmentName,
          ParamType.String,
        ),
        'roleName': serializeParam(
          _roleName,
          ParamType.String,
        ),
      }.withoutNulls;

  static UserProfileStruct fromSerializableMap(Map<String, dynamic> data) =>
      UserProfileStruct(
        id: deserializeParam(
          data['id'],
          ParamType.int,
          false,
        ),
        name: deserializeParam(
          data['name'],
          ParamType.String,
          false,
        ),
        surname: deserializeParam(
          data['surname'],
          ParamType.String,
          false,
        ),
        cellphoneNumber: deserializeParam(
          data['cellphoneNumber'],
          ParamType.String,
          false,
        ),
        employeeRegistration: deserializeParam(
          data['employeeRegistration'],
          ParamType.int,
          false,
        ),
        departmentName: deserializeParam(
          data['departmentName'],
          ParamType.String,
          false,
        ),
        roleName: deserializeParam(
          data['roleName'],
          ParamType.String,
          false,
        ),
      );

  @override
  String toString() => 'UserProfileStruct(${toMap()})';

  @override
  bool operator ==(Object other) {
    return other is UserProfileStruct &&
        id == other.id &&
        name == other.name &&
        surname == other.surname &&
        cellphoneNumber == other.cellphoneNumber &&
        employeeRegistration == other.employeeRegistration &&
        departmentName == other.departmentName &&
        roleName == other.roleName;
  }

  @override
  int get hashCode => const ListEquality().hash([
        id,
        name,
        surname,
        cellphoneNumber,
        employeeRegistration,
        departmentName,
        roleName
      ]);
}

UserProfileStruct createUserProfileStruct({
  int? id,
  String? name,
  String? surname,
  String? cellphoneNumber,
  int? employeeRegistration,
  String? departmentName,
  String? roleName,
}) =>
    UserProfileStruct(
      id: id,
      name: name,
      surname: surname,
      cellphoneNumber: cellphoneNumber,
      employeeRegistration: employeeRegistration,
      departmentName: departmentName,
      roleName: roleName,
    );
