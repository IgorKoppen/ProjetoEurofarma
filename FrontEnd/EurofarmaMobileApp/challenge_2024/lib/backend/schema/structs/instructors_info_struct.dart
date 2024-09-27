// ignore_for_file: unnecessary_getters_setters

import '/backend/schema/util/schema_util.dart';

import 'index.dart';
import '/flutter_flow/flutter_flow_util.dart';

class InstructorsInfoStruct extends BaseStruct {
  InstructorsInfoStruct({
    int? id,
    String? name,
    String? surname,
    String? cellphoneNumber,
    int? employeeRegistration,
  })  : _id = id,
        _name = name,
        _surname = surname,
        _cellphoneNumber = cellphoneNumber,
        _employeeRegistration = employeeRegistration;

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

  static InstructorsInfoStruct fromMap(Map<String, dynamic> data) =>
      InstructorsInfoStruct(
        id: castToType<int>(data['id']),
        name: data['name'] as String?,
        surname: data['surname'] as String?,
        cellphoneNumber: data['cellphoneNumber'] as String?,
        employeeRegistration: castToType<int>(data['employeeRegistration']),
      );

  static InstructorsInfoStruct? maybeFromMap(dynamic data) => data is Map
      ? InstructorsInfoStruct.fromMap(data.cast<String, dynamic>())
      : null;

  Map<String, dynamic> toMap() => {
        'id': _id,
        'name': _name,
        'surname': _surname,
        'cellphoneNumber': _cellphoneNumber,
        'employeeRegistration': _employeeRegistration,
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
      }.withoutNulls;

  static InstructorsInfoStruct fromSerializableMap(Map<String, dynamic> data) =>
      InstructorsInfoStruct(
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
      );

  @override
  String toString() => 'InstructorsInfoStruct(${toMap()})';

  @override
  bool operator ==(Object other) {
    return other is InstructorsInfoStruct &&
        id == other.id &&
        name == other.name &&
        surname == other.surname &&
        cellphoneNumber == other.cellphoneNumber &&
        employeeRegistration == other.employeeRegistration;
  }

  @override
  int get hashCode => const ListEquality()
      .hash([id, name, surname, cellphoneNumber, employeeRegistration]);
}

InstructorsInfoStruct createInstructorsInfoStruct({
  int? id,
  String? name,
  String? surname,
  String? cellphoneNumber,
  int? employeeRegistration,
}) =>
    InstructorsInfoStruct(
      id: id,
      name: name,
      surname: surname,
      cellphoneNumber: cellphoneNumber,
      employeeRegistration: employeeRegistration,
    );
