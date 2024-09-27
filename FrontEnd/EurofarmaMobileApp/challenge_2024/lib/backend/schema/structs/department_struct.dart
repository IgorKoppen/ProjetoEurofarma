// ignore_for_file: unnecessary_getters_setters

import '/backend/schema/util/schema_util.dart';

import 'index.dart';
import '/flutter_flow/flutter_flow_util.dart';

class DepartmentStruct extends BaseStruct {
  DepartmentStruct({
    int? id,
    String? departName,
  })  : _id = id,
        _departName = departName;

  // "id" field.
  int? _id;
  int get id => _id ?? 0;
  set id(int? val) => _id = val;

  void incrementId(int amount) => id = id + amount;

  bool hasId() => _id != null;

  // "departName" field.
  String? _departName;
  String get departName => _departName ?? '';
  set departName(String? val) => _departName = val;

  bool hasDepartName() => _departName != null;

  static DepartmentStruct fromMap(Map<String, dynamic> data) =>
      DepartmentStruct(
        id: castToType<int>(data['id']),
        departName: data['departName'] as String?,
      );

  static DepartmentStruct? maybeFromMap(dynamic data) => data is Map
      ? DepartmentStruct.fromMap(data.cast<String, dynamic>())
      : null;

  Map<String, dynamic> toMap() => {
        'id': _id,
        'departName': _departName,
      }.withoutNulls;

  @override
  Map<String, dynamic> toSerializableMap() => {
        'id': serializeParam(
          _id,
          ParamType.int,
        ),
        'departName': serializeParam(
          _departName,
          ParamType.String,
        ),
      }.withoutNulls;

  static DepartmentStruct fromSerializableMap(Map<String, dynamic> data) =>
      DepartmentStruct(
        id: deserializeParam(
          data['id'],
          ParamType.int,
          false,
        ),
        departName: deserializeParam(
          data['departName'],
          ParamType.String,
          false,
        ),
      );

  @override
  String toString() => 'DepartmentStruct(${toMap()})';

  @override
  bool operator ==(Object other) {
    return other is DepartmentStruct &&
        id == other.id &&
        departName == other.departName;
  }

  @override
  int get hashCode => const ListEquality().hash([id, departName]);
}

DepartmentStruct createDepartmentStruct({
  int? id,
  String? departName,
}) =>
    DepartmentStruct(
      id: id,
      departName: departName,
    );
