// ignore_for_file: unnecessary_getters_setters

import '/backend/schema/util/schema_util.dart';

import 'index.dart';
import '/flutter_flow/flutter_flow_util.dart';

class AnswersStruct extends BaseStruct {
  AnswersStruct({
    int? id,
    String? answer,
    bool? correct,
    int? question,
  })  : _id = id,
        _answer = answer,
        _correct = correct,
        _question = question;

  // "id" field.
  int? _id;
  int get id => _id ?? 0;
  set id(int? val) => _id = val;

  void incrementId(int amount) => id = id + amount;

  bool hasId() => _id != null;

  // "answer" field.
  String? _answer;
  String get answer => _answer ?? '';
  set answer(String? val) => _answer = val;

  bool hasAnswer() => _answer != null;

  // "correct" field.
  bool? _correct;
  bool get correct => _correct ?? false;
  set correct(bool? val) => _correct = val;

  bool hasCorrect() => _correct != null;

  // "question" field.
  int? _question;
  int get question => _question ?? 0;
  set question(int? val) => _question = val;

  void incrementQuestion(int amount) => question = question + amount;

  bool hasQuestion() => _question != null;

  static AnswersStruct fromMap(Map<String, dynamic> data) => AnswersStruct(
        id: castToType<int>(data['id']),
        answer: data['answer'] as String?,
        correct: data['correct'] as bool?,
        question: castToType<int>(data['question']),
      );

  static AnswersStruct? maybeFromMap(dynamic data) =>
      data is Map ? AnswersStruct.fromMap(data.cast<String, dynamic>()) : null;

  Map<String, dynamic> toMap() => {
        'id': _id,
        'answer': _answer,
        'correct': _correct,
        'question': _question,
      }.withoutNulls;

  @override
  Map<String, dynamic> toSerializableMap() => {
        'id': serializeParam(
          _id,
          ParamType.int,
        ),
        'answer': serializeParam(
          _answer,
          ParamType.String,
        ),
        'correct': serializeParam(
          _correct,
          ParamType.bool,
        ),
        'question': serializeParam(
          _question,
          ParamType.int,
        ),
      }.withoutNulls;

  static AnswersStruct fromSerializableMap(Map<String, dynamic> data) =>
      AnswersStruct(
        id: deserializeParam(
          data['id'],
          ParamType.int,
          false,
        ),
        answer: deserializeParam(
          data['answer'],
          ParamType.String,
          false,
        ),
        correct: deserializeParam(
          data['correct'],
          ParamType.bool,
          false,
        ),
        question: deserializeParam(
          data['question'],
          ParamType.int,
          false,
        ),
      );

  @override
  String toString() => 'AnswersStruct(${toMap()})';

  @override
  bool operator ==(Object other) {
    return other is AnswersStruct &&
        id == other.id &&
        answer == other.answer &&
        correct == other.correct &&
        question == other.question;
  }

  @override
  int get hashCode =>
      const ListEquality().hash([id, answer, correct, question]);
}

AnswersStruct createAnswersStruct({
  int? id,
  String? answer,
  bool? correct,
  int? question,
}) =>
    AnswersStruct(
      id: id,
      answer: answer,
      correct: correct,
      question: question,
    );
