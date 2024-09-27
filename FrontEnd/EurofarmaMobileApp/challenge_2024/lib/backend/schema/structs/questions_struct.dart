// ignore_for_file: unnecessary_getters_setters


import 'index.dart';
import '/flutter_flow/flutter_flow_util.dart';

class QuestionsStruct extends BaseStruct {
  QuestionsStruct({
    int? id,
    String? question,
    List<AnswersStruct>? answers,
  })  : _id = id,
        _question = question,
        _answers = answers;

  // "id" field.
  int? _id;
  int get id => _id ?? 0;
  set id(int? val) => _id = val;

  void incrementId(int amount) => id = id + amount;

  bool hasId() => _id != null;

  // "question" field.
  String? _question;
  String get question => _question ?? '';
  set question(String? val) => _question = val;

  bool hasQuestion() => _question != null;

  // "answers" field.
  List<AnswersStruct>? _answers;
  List<AnswersStruct> get answers => _answers ?? const [];
  set answers(List<AnswersStruct>? val) => _answers = val;

  void updateAnswers(Function(List<AnswersStruct>) updateFn) {
    updateFn(_answers ??= []);
  }

  bool hasAnswers() => _answers != null;

  static QuestionsStruct fromMap(Map<String, dynamic> data) => QuestionsStruct(
        id: castToType<int>(data['id']),
        question: data['question'] as String?,
        answers: getStructList(
          data['answers'],
          AnswersStruct.fromMap,
        ),
      );

  static QuestionsStruct? maybeFromMap(dynamic data) => data is Map
      ? QuestionsStruct.fromMap(data.cast<String, dynamic>())
      : null;

  Map<String, dynamic> toMap() => {
        'id': _id,
        'question': _question,
        'answers': _answers?.map((e) => e.toMap()).toList(),
      }.withoutNulls;

  @override
  Map<String, dynamic> toSerializableMap() => {
        'id': serializeParam(
          _id,
          ParamType.int,
        ),
        'question': serializeParam(
          _question,
          ParamType.String,
        ),
        'answers': serializeParam(
          _answers,
          ParamType.DataStruct,
          isList: true,
        ),
      }.withoutNulls;

  static QuestionsStruct fromSerializableMap(Map<String, dynamic> data) =>
      QuestionsStruct(
        id: deserializeParam(
          data['id'],
          ParamType.int,
          false,
        ),
        question: deserializeParam(
          data['question'],
          ParamType.String,
          false,
        ),
        answers: deserializeStructParam<AnswersStruct>(
          data['answers'],
          ParamType.DataStruct,
          true,
          structBuilder: AnswersStruct.fromSerializableMap,
        ),
      );

  @override
  String toString() => 'QuestionsStruct(${toMap()})';

  @override
  bool operator ==(Object other) {
    const listEquality = ListEquality();
    return other is QuestionsStruct &&
        id == other.id &&
        question == other.question &&
        listEquality.equals(answers, other.answers);
  }

  @override
  int get hashCode => const ListEquality().hash([id, question, answers]);
}

QuestionsStruct createQuestionsStruct({
  int? id,
  String? question,
}) =>
    QuestionsStruct(
      id: id,
      question: question,
    );
