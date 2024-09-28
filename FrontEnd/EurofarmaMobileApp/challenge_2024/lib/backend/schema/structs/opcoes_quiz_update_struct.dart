// ignore_for_file: unnecessary_getters_setters

import '/backend/schema/util/schema_util.dart';

import 'index.dart';
import '/flutter_flow/flutter_flow_util.dart';

class OpcoesQuizUpdateStruct extends BaseStruct {
  OpcoesQuizUpdateStruct({
    int? id,
    String? optionName,
    bool? isCorrect,
  })  : _id = id,
        _optionName = optionName,
        _isCorrect = isCorrect;

  // "id" field.
  int? _id;
  int get id => _id ?? 0;
  set id(int? val) => _id = val;

  void incrementId(int amount) => id = id + amount;

  bool hasId() => _id != null;

  // "optionName" field.
  String? _optionName;
  String get optionName => _optionName ?? '';
  set optionName(String? val) => _optionName = val;

  bool hasOptionName() => _optionName != null;

  // "isCorrect" field.
  bool? _isCorrect;
  bool get isCorrect => _isCorrect ?? false;
  set isCorrect(bool? val) => _isCorrect = val;

  bool hasIsCorrect() => _isCorrect != null;

  static OpcoesQuizUpdateStruct fromMap(Map<String, dynamic> data) =>
      OpcoesQuizUpdateStruct(
        id: castToType<int>(data['id']),
        optionName: data['optionName'] as String?,
        isCorrect: data['isCorrect'] as bool?,
      );

  static OpcoesQuizUpdateStruct? maybeFromMap(dynamic data) => data is Map
      ? OpcoesQuizUpdateStruct.fromMap(data.cast<String, dynamic>())
      : null;

  Map<String, dynamic> toMap() => {
        'id': _id,
        'optionName': _optionName,
        'isCorrect': _isCorrect,
      }.withoutNulls;

  @override
  Map<String, dynamic> toSerializableMap() => {
        'id': serializeParam(
          _id,
          ParamType.int,
        ),
        'optionName': serializeParam(
          _optionName,
          ParamType.String,
        ),
        'isCorrect': serializeParam(
          _isCorrect,
          ParamType.bool,
        ),
      }.withoutNulls;

  static OpcoesQuizUpdateStruct fromSerializableMap(
          Map<String, dynamic> data) =>
      OpcoesQuizUpdateStruct(
        id: deserializeParam(
          data['id'],
          ParamType.int,
          false,
        ),
        optionName: deserializeParam(
          data['optionName'],
          ParamType.String,
          false,
        ),
        isCorrect: deserializeParam(
          data['isCorrect'],
          ParamType.bool,
          false,
        ),
      );

  @override
  String toString() => 'OpcoesQuizUpdateStruct(${toMap()})';

  @override
  bool operator ==(Object other) {
    return other is OpcoesQuizUpdateStruct &&
        id == other.id &&
        optionName == other.optionName &&
        isCorrect == other.isCorrect;
  }

  @override
  int get hashCode => const ListEquality().hash([id, optionName, isCorrect]);
}

OpcoesQuizUpdateStruct createOpcoesQuizUpdateStruct({
  int? id,
  String? optionName,
  bool? isCorrect,
}) =>
    OpcoesQuizUpdateStruct(
      id: id,
      optionName: optionName,
      isCorrect: isCorrect,
    );
