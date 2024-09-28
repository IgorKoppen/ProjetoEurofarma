// ignore_for_file: unnecessary_getters_setters

import '/backend/schema/util/schema_util.dart';

import 'index.dart';
import '/flutter_flow/flutter_flow_util.dart';

class TagsStruct extends BaseStruct {
  TagsStruct({
    int? id,
    String? name,
    String? color,
  })  : _id = id,
        _name = name,
        _color = color;

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

  // "color" field.
  String? _color;
  String get color => _color ?? '';
  set color(String? val) => _color = val;

  bool hasColor() => _color != null;

  static TagsStruct fromMap(Map<String, dynamic> data) => TagsStruct(
        id: castToType<int>(data['id']),
        name: data['name'] as String?,
        color: data['color'] as String?,
      );

  static TagsStruct? maybeFromMap(dynamic data) =>
      data is Map ? TagsStruct.fromMap(data.cast<String, dynamic>()) : null;

  Map<String, dynamic> toMap() => {
        'id': _id,
        'name': _name,
        'color': _color,
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
        'color': serializeParam(
          _color,
          ParamType.String,
        ),
      }.withoutNulls;

  static TagsStruct fromSerializableMap(Map<String, dynamic> data) =>
      TagsStruct(
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
        color: deserializeParam(
          data['color'],
          ParamType.String,
          false,
        ),
      );

  @override
  String toString() => 'TagsStruct(${toMap()})';

  @override
  bool operator ==(Object other) {
    return other is TagsStruct &&
        id == other.id &&
        name == other.name &&
        color == other.color;
  }

  @override
  int get hashCode => const ListEquality().hash([id, name, color]);
}

TagsStruct createTagsStruct({
  int? id,
  String? name,
  String? color,
}) =>
    TagsStruct(
      id: id,
      name: name,
      color: color,
    );
