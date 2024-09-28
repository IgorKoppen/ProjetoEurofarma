// ignore_for_file: unnecessary_getters_setters

import '/backend/schema/util/schema_util.dart';

import 'index.dart';
import '/flutter_flow/flutter_flow_util.dart';

class OpcoesQuizStruct extends BaseStruct {
  OpcoesQuizStruct({
    String? optionName,
    bool? isCorrect,
  })  : _optionName = optionName,
        _isCorrect = isCorrect;

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

  static OpcoesQuizStruct fromMap(Map<String, dynamic> data) =>
      OpcoesQuizStruct(
        optionName: data['optionName'] as String?,
        isCorrect: data['isCorrect'] as bool?,
      );

  static OpcoesQuizStruct? maybeFromMap(dynamic data) => data is Map
      ? OpcoesQuizStruct.fromMap(data.cast<String, dynamic>())
      : null;

  Map<String, dynamic> toMap() => {
        'optionName': _optionName,
        'isCorrect': _isCorrect,
      }.withoutNulls;

  @override
  Map<String, dynamic> toSerializableMap() => {
        'optionName': serializeParam(
          _optionName,
          ParamType.String,
        ),
        'isCorrect': serializeParam(
          _isCorrect,
          ParamType.bool,
        ),
      }.withoutNulls;

  static OpcoesQuizStruct fromSerializableMap(Map<String, dynamic> data) =>
      OpcoesQuizStruct(
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
  String toString() => 'OpcoesQuizStruct(${toMap()})';

  @override
  bool operator ==(Object other) {
    return other is OpcoesQuizStruct &&
        optionName == other.optionName &&
        isCorrect == other.isCorrect;
  }

  @override
  int get hashCode => const ListEquality().hash([optionName, isCorrect]);
}

OpcoesQuizStruct createOpcoesQuizStruct({
  String? optionName,
  bool? isCorrect,
}) =>
    OpcoesQuizStruct(
      optionName: optionName,
      isCorrect: isCorrect,
    );
