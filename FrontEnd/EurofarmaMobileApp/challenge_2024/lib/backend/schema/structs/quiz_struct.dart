// ignore_for_file: unnecessary_getters_setters


import 'index.dart';
import '/flutter_flow/flutter_flow_util.dart';

class QuizStruct extends BaseStruct {
  QuizStruct({
    int? id,
    String? nome,
    String? description,
    int? notaMinima,
    int? questionsNumber,
    List<QuestionsStruct>? questions,
  })  : _id = id,
        _nome = nome,
        _description = description,
        _notaMinima = notaMinima,
        _questionsNumber = questionsNumber,
        _questions = questions;

  // "id" field.
  int? _id;
  int get id => _id ?? 0;
  set id(int? val) => _id = val;

  void incrementId(int amount) => id = id + amount;

  bool hasId() => _id != null;

  // "nome" field.
  String? _nome;
  String get nome => _nome ?? '';
  set nome(String? val) => _nome = val;

  bool hasNome() => _nome != null;

  // "description" field.
  String? _description;
  String get description => _description ?? '';
  set description(String? val) => _description = val;

  bool hasDescription() => _description != null;

  // "notaMinima" field.
  int? _notaMinima;
  int get notaMinima => _notaMinima ?? 0;
  set notaMinima(int? val) => _notaMinima = val;

  void incrementNotaMinima(int amount) => notaMinima = notaMinima + amount;

  bool hasNotaMinima() => _notaMinima != null;

  // "questionsNumber" field.
  int? _questionsNumber;
  int get questionsNumber => _questionsNumber ?? 0;
  set questionsNumber(int? val) => _questionsNumber = val;

  void incrementQuestionsNumber(int amount) =>
      questionsNumber = questionsNumber + amount;

  bool hasQuestionsNumber() => _questionsNumber != null;

  // "questions" field.
  List<QuestionsStruct>? _questions;
  List<QuestionsStruct> get questions => _questions ?? const [];
  set questions(List<QuestionsStruct>? val) => _questions = val;

  void updateQuestions(Function(List<QuestionsStruct>) updateFn) {
    updateFn(_questions ??= []);
  }

  bool hasQuestions() => _questions != null;

  static QuizStruct fromMap(Map<String, dynamic> data) => QuizStruct(
        id: castToType<int>(data['id']),
        nome: data['nome'] as String?,
        description: data['description'] as String?,
        notaMinima: castToType<int>(data['notaMinima']),
        questionsNumber: castToType<int>(data['questionsNumber']),
        questions: getStructList(
          data['questions'],
          QuestionsStruct.fromMap,
        ),
      );

  static QuizStruct? maybeFromMap(dynamic data) =>
      data is Map ? QuizStruct.fromMap(data.cast<String, dynamic>()) : null;

  Map<String, dynamic> toMap() => {
        'id': _id,
        'nome': _nome,
        'description': _description,
        'notaMinima': _notaMinima,
        'questionsNumber': _questionsNumber,
        'questions': _questions?.map((e) => e.toMap()).toList(),
      }.withoutNulls;

  @override
  Map<String, dynamic> toSerializableMap() => {
        'id': serializeParam(
          _id,
          ParamType.int,
        ),
        'nome': serializeParam(
          _nome,
          ParamType.String,
        ),
        'description': serializeParam(
          _description,
          ParamType.String,
        ),
        'notaMinima': serializeParam(
          _notaMinima,
          ParamType.int,
        ),
        'questionsNumber': serializeParam(
          _questionsNumber,
          ParamType.int,
        ),
        'questions': serializeParam(
          _questions,
          ParamType.DataStruct,
          isList: true,
        ),
      }.withoutNulls;

  static QuizStruct fromSerializableMap(Map<String, dynamic> data) =>
      QuizStruct(
        id: deserializeParam(
          data['id'],
          ParamType.int,
          false,
        ),
        nome: deserializeParam(
          data['nome'],
          ParamType.String,
          false,
        ),
        description: deserializeParam(
          data['description'],
          ParamType.String,
          false,
        ),
        notaMinima: deserializeParam(
          data['notaMinima'],
          ParamType.int,
          false,
        ),
        questionsNumber: deserializeParam(
          data['questionsNumber'],
          ParamType.int,
          false,
        ),
        questions: deserializeStructParam<QuestionsStruct>(
          data['questions'],
          ParamType.DataStruct,
          true,
          structBuilder: QuestionsStruct.fromSerializableMap,
        ),
      );

  @override
  String toString() => 'QuizStruct(${toMap()})';

  @override
  bool operator ==(Object other) {
    const listEquality = ListEquality();
    return other is QuizStruct &&
        id == other.id &&
        nome == other.nome &&
        description == other.description &&
        notaMinima == other.notaMinima &&
        questionsNumber == other.questionsNumber &&
        listEquality.equals(questions, other.questions);
  }

  @override
  int get hashCode => const ListEquality()
      .hash([id, nome, description, notaMinima, questionsNumber, questions]);
}

QuizStruct createQuizStruct({
  int? id,
  String? nome,
  String? description,
  int? notaMinima,
  int? questionsNumber,
}) =>
    QuizStruct(
      id: id,
      nome: nome,
      description: description,
      notaMinima: notaMinima,
      questionsNumber: questionsNumber,
    );
