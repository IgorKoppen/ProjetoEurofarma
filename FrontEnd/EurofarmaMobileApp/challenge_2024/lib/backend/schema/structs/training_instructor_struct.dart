// ignore_for_file: unnecessary_getters_setters


import 'index.dart';
import '/flutter_flow/flutter_flow_util.dart';

class TrainingInstructorStruct extends BaseStruct {
  TrainingInstructorStruct({
    int? id,
    String? name,
    String? code,
    String? password,
    String? description,
    List<InstructorsInfoStruct>? instructorsInfo,
    List<TagsStruct>? tags,
    bool? opened,
    String? creationDate,
    String? closingDate,
    bool? hasQuiz,
    QuizStruct? quiz,
    List<DepartmentStruct>? departments,
  })  : _id = id,
        _name = name,
        _code = code,
        _password = password,
        _description = description,
        _instructorsInfo = instructorsInfo,
        _tags = tags,
        _opened = opened,
        _creationDate = creationDate,
        _closingDate = closingDate,
        _hasQuiz = hasQuiz,
        _quiz = quiz,
        _departments = departments;

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

  // "code" field.
  String? _code;
  String get code => _code ?? '';
  set code(String? val) => _code = val;

  bool hasCode() => _code != null;

  // "password" field.
  String? _password;
  String get password => _password ?? '';
  set password(String? val) => _password = val;

  bool hasPassword() => _password != null;

  // "description" field.
  String? _description;
  String get description => _description ?? '';
  set description(String? val) => _description = val;

  bool hasDescription() => _description != null;

  // "instructorsInfo" field.
  List<InstructorsInfoStruct>? _instructorsInfo;
  List<InstructorsInfoStruct> get instructorsInfo =>
      _instructorsInfo ?? const [];
  set instructorsInfo(List<InstructorsInfoStruct>? val) =>
      _instructorsInfo = val;

  void updateInstructorsInfo(Function(List<InstructorsInfoStruct>) updateFn) {
    updateFn(_instructorsInfo ??= []);
  }

  bool hasInstructorsInfo() => _instructorsInfo != null;

  // "tags" field.
  List<TagsStruct>? _tags;
  List<TagsStruct> get tags => _tags ?? const [];
  set tags(List<TagsStruct>? val) => _tags = val;

  void updateTags(Function(List<TagsStruct>) updateFn) {
    updateFn(_tags ??= []);
  }

  bool hasTags() => _tags != null;

  // "opened" field.
  bool? _opened;
  bool get opened => _opened ?? false;
  set opened(bool? val) => _opened = val;

  bool hasOpened() => _opened != null;

  // "creation_date" field.
  String? _creationDate;
  String get creationDate => _creationDate ?? '';
  set creationDate(String? val) => _creationDate = val;

  bool hasCreationDate() => _creationDate != null;

  // "closing_date" field.
  String? _closingDate;
  String get closingDate => _closingDate ?? '';
  set closingDate(String? val) => _closingDate = val;

  bool hasClosingDate() => _closingDate != null;

  // "hasQuiz" field.
  bool? _hasQuiz;
  bool get hasQuiz => _hasQuiz ?? false;
  set hasQuiz(bool? val) => _hasQuiz = val;

  bool hasHasQuiz() => _hasQuiz != null;

  // "quiz" field.
  QuizStruct? _quiz;
  QuizStruct get quiz => _quiz ?? QuizStruct();
  set quiz(QuizStruct? val) => _quiz = val;

  void updateQuiz(Function(QuizStruct) updateFn) {
    updateFn(_quiz ??= QuizStruct());
  }

  bool hasQuizField() => _quiz != null;

  // "departments" field.
  List<DepartmentStruct>? _departments;
  List<DepartmentStruct> get departments => _departments ?? const [];
  set departments(List<DepartmentStruct>? val) => _departments = val;

  void updateDepartments(Function(List<DepartmentStruct>) updateFn) {
    updateFn(_departments ??= []);
  }

  bool hasDepartments() => _departments != null;

  static TrainingInstructorStruct fromMap(Map<String, dynamic> data) =>
      TrainingInstructorStruct(
        id: castToType<int>(data['id']),
        name: data['name'] as String?,
        code: data['code'] as String?,
        password: data['password'] as String?,
        description: data['description'] as String?,
        instructorsInfo: getStructList(
          data['instructorsInfo'],
          InstructorsInfoStruct.fromMap,
        ),
        tags: getStructList(
          data['tags'],
          TagsStruct.fromMap,
        ),
        opened: data['opened'] as bool?,
        creationDate: data['creation_date'] as String?,
        closingDate: data['closing_date'] as String?,
        hasQuiz: data['hasQuiz'] as bool?,
        quiz: QuizStruct.maybeFromMap(data['quiz']),
        departments: getStructList(
          data['departments'],
          DepartmentStruct.fromMap,
        ),
      );

  static TrainingInstructorStruct? maybeFromMap(dynamic data) => data is Map
      ? TrainingInstructorStruct.fromMap(data.cast<String, dynamic>())
      : null;

  Map<String, dynamic> toMap() => {
        'id': _id,
        'name': _name,
        'code': _code,
        'password': _password,
        'description': _description,
        'instructorsInfo': _instructorsInfo?.map((e) => e.toMap()).toList(),
        'tags': _tags?.map((e) => e.toMap()).toList(),
        'opened': _opened,
        'creation_date': _creationDate,
        'closing_date': _closingDate,
        'hasQuiz': _hasQuiz,
        'quiz': _quiz?.toMap(),
        'departments': _departments?.map((e) => e.toMap()).toList(),
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
        'code': serializeParam(
          _code,
          ParamType.String,
        ),
        'password': serializeParam(
          _password,
          ParamType.String,
        ),
        'description': serializeParam(
          _description,
          ParamType.String,
        ),
        'instructorsInfo': serializeParam(
          _instructorsInfo,
          ParamType.DataStruct,
          isList: true,
        ),
        'tags': serializeParam(
          _tags,
          ParamType.DataStruct,
          isList: true,
        ),
        'opened': serializeParam(
          _opened,
          ParamType.bool,
        ),
        'creation_date': serializeParam(
          _creationDate,
          ParamType.String,
        ),
        'closing_date': serializeParam(
          _closingDate,
          ParamType.String,
        ),
        'hasQuiz': serializeParam(
          _hasQuiz,
          ParamType.bool,
        ),
        'quiz': serializeParam(
          _quiz,
          ParamType.DataStruct,
        ),
        'departments': serializeParam(
          _departments,
          ParamType.DataStruct,
          isList: true,
        ),
      }.withoutNulls;

  static TrainingInstructorStruct fromSerializableMap(
          Map<String, dynamic> data) =>
      TrainingInstructorStruct(
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
        code: deserializeParam(
          data['code'],
          ParamType.String,
          false,
        ),
        password: deserializeParam(
          data['password'],
          ParamType.String,
          false,
        ),
        description: deserializeParam(
          data['description'],
          ParamType.String,
          false,
        ),
        instructorsInfo: deserializeStructParam<InstructorsInfoStruct>(
          data['instructorsInfo'],
          ParamType.DataStruct,
          true,
          structBuilder: InstructorsInfoStruct.fromSerializableMap,
        ),
        tags: deserializeStructParam<TagsStruct>(
          data['tags'],
          ParamType.DataStruct,
          true,
          structBuilder: TagsStruct.fromSerializableMap,
        ),
        opened: deserializeParam(
          data['opened'],
          ParamType.bool,
          false,
        ),
        creationDate: deserializeParam(
          data['creation_date'],
          ParamType.String,
          false,
        ),
        closingDate: deserializeParam(
          data['closing_date'],
          ParamType.String,
          false,
        ),
        hasQuiz: deserializeParam(
          data['hasQuiz'],
          ParamType.bool,
          false,
        ),
        quiz: deserializeStructParam(
          data['quiz'],
          ParamType.DataStruct,
          false,
          structBuilder: QuizStruct.fromSerializableMap,
        ),
        departments: deserializeStructParam<DepartmentStruct>(
          data['departments'],
          ParamType.DataStruct,
          true,
          structBuilder: DepartmentStruct.fromSerializableMap,
        ),
      );

  @override
  String toString() => 'TrainingInstructorStruct(${toMap()})';

  @override
  bool operator ==(Object other) {
    const listEquality = ListEquality();
    return other is TrainingInstructorStruct &&
        id == other.id &&
        name == other.name &&
        code == other.code &&
        password == other.password &&
        description == other.description &&
        listEquality.equals(instructorsInfo, other.instructorsInfo) &&
        listEquality.equals(tags, other.tags) &&
        opened == other.opened &&
        creationDate == other.creationDate &&
        closingDate == other.closingDate &&
        hasQuiz == other.hasQuiz &&
        quiz == other.quiz &&
        listEquality.equals(departments, other.departments);
  }

  @override
  int get hashCode => const ListEquality().hash([
        id,
        name,
        code,
        password,
        description,
        instructorsInfo,
        tags,
        opened,
        creationDate,
        closingDate,
        hasQuiz,
        quiz,
        departments
      ]);
}

TrainingInstructorStruct createTrainingInstructorStruct({
  int? id,
  String? name,
  String? code,
  String? password,
  String? description,
  bool? opened,
  String? creationDate,
  String? closingDate,
  bool? hasQuiz,
  QuizStruct? quiz,
}) =>
    TrainingInstructorStruct(
      id: id,
      name: name,
      code: code,
      password: password,
      description: description,
      opened: opened,
      creationDate: creationDate,
      closingDate: closingDate,
      hasQuiz: hasQuiz,
      quiz: quiz ?? QuizStruct(),
    );
