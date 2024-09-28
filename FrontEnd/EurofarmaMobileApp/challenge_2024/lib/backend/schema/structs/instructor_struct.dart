// ignore_for_file: unnecessary_getters_setters

import '/backend/schema/util/schema_util.dart';

import 'index.dart';
import '/flutter_flow/flutter_flow_util.dart';

class InstructorStruct extends BaseStruct {
  InstructorStruct({
    int? id,
    String? name,
    String? surname,
    String? fullName,
    int? employeeRegistrarion,
  })  : _id = id,
        _name = name,
        _surname = surname,
        _fullName = fullName,
        _employeeRegistrarion = employeeRegistrarion;

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

  // "fullName" field.
  String? _fullName;
  String get fullName => _fullName ?? '';
  set fullName(String? val) => _fullName = val;

  bool hasFullName() => _fullName != null;

  // "employeeRegistrarion" field.
  int? _employeeRegistrarion;
  int get employeeRegistrarion => _employeeRegistrarion ?? 0;
  set employeeRegistrarion(int? val) => _employeeRegistrarion = val;

  void incrementEmployeeRegistrarion(int amount) =>
      employeeRegistrarion = employeeRegistrarion + amount;

  bool hasEmployeeRegistrarion() => _employeeRegistrarion != null;

  static InstructorStruct fromMap(Map<String, dynamic> data) =>
      InstructorStruct(
        id: castToType<int>(data['id']),
        name: data['name'] as String?,
        surname: data['surname'] as String?,
        fullName: data['fullName'] as String?,
        employeeRegistrarion: castToType<int>(data['employeeRegistrarion']),
      );

  static InstructorStruct? maybeFromMap(dynamic data) => data is Map
      ? InstructorStruct.fromMap(data.cast<String, dynamic>())
      : null;

  Map<String, dynamic> toMap() => {
        'id': _id,
        'name': _name,
        'surname': _surname,
        'fullName': _fullName,
        'employeeRegistrarion': _employeeRegistrarion,
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
        'fullName': serializeParam(
          _fullName,
          ParamType.String,
        ),
        'employeeRegistrarion': serializeParam(
          _employeeRegistrarion,
          ParamType.int,
        ),
      }.withoutNulls;

  static InstructorStruct fromSerializableMap(Map<String, dynamic> data) =>
      InstructorStruct(
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
        fullName: deserializeParam(
          data['fullName'],
          ParamType.String,
          false,
        ),
        employeeRegistrarion: deserializeParam(
          data['employeeRegistrarion'],
          ParamType.int,
          false,
        ),
      );

  @override
  String toString() => 'InstructorStruct(${toMap()})';

  @override
  bool operator ==(Object other) {
    return other is InstructorStruct &&
        id == other.id &&
        name == other.name &&
        surname == other.surname &&
        fullName == other.fullName &&
        employeeRegistrarion == other.employeeRegistrarion;
  }

  @override
  int get hashCode => const ListEquality()
      .hash([id, name, surname, fullName, employeeRegistrarion]);
}

InstructorStruct createInstructorStruct({
  int? id,
  String? name,
  String? surname,
  String? fullName,
  int? employeeRegistrarion,
}) =>
    InstructorStruct(
      id: id,
      name: name,
      surname: surname,
      fullName: fullName,
      employeeRegistrarion: employeeRegistrarion,
    );
