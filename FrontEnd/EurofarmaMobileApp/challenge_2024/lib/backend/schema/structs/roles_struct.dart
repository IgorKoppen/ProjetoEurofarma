// ignore_for_file: unnecessary_getters_setters

import '/backend/schema/util/schema_util.dart';

import 'index.dart';
import '/flutter_flow/flutter_flow_util.dart';

class RolesStruct extends BaseStruct {
  RolesStruct({
    int? id,
    String? roleName,
  })  : _id = id,
        _roleName = roleName;

  // "id" field.
  int? _id;
  int get id => _id ?? 0;
  set id(int? val) => _id = val;

  void incrementId(int amount) => id = id + amount;

  bool hasId() => _id != null;

  // "roleName" field.
  String? _roleName;
  String get roleName => _roleName ?? '';
  set roleName(String? val) => _roleName = val;

  bool hasRoleName() => _roleName != null;

  static RolesStruct fromMap(Map<String, dynamic> data) => RolesStruct(
        id: castToType<int>(data['id']),
        roleName: data['roleName'] as String?,
      );

  static RolesStruct? maybeFromMap(dynamic data) =>
      data is Map ? RolesStruct.fromMap(data.cast<String, dynamic>()) : null;

  Map<String, dynamic> toMap() => {
        'id': _id,
        'roleName': _roleName,
      }.withoutNulls;

  @override
  Map<String, dynamic> toSerializableMap() => {
        'id': serializeParam(
          _id,
          ParamType.int,
        ),
        'roleName': serializeParam(
          _roleName,
          ParamType.String,
        ),
      }.withoutNulls;

  static RolesStruct fromSerializableMap(Map<String, dynamic> data) =>
      RolesStruct(
        id: deserializeParam(
          data['id'],
          ParamType.int,
          false,
        ),
        roleName: deserializeParam(
          data['roleName'],
          ParamType.String,
          false,
        ),
      );

  @override
  String toString() => 'RolesStruct(${toMap()})';

  @override
  bool operator ==(Object other) {
    return other is RolesStruct && id == other.id && roleName == other.roleName;
  }

  @override
  int get hashCode => const ListEquality().hash([id, roleName]);
}

RolesStruct createRolesStruct({
  int? id,
  String? roleName,
}) =>
    RolesStruct(
      id: id,
      roleName: roleName,
    );
