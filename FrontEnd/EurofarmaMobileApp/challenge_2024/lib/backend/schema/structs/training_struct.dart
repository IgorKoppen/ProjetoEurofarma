// ignore_for_file: unnecessary_getters_setters


import 'index.dart';
import '/flutter_flow/flutter_flow_util.dart';

class TrainingStruct extends BaseStruct {
  TrainingStruct({
    int? id,
    String? name,
    String? description,
    List<InstructorsInfoStruct>? instructorsInfo,
    List<TagsStruct>? tags,
    String? registrationDate,
    String? signature,
    bool? opened,
    String? creationDate,
    String? closingDate,
  })  : _id = id,
        _name = name,
        _description = description,
        _instructorsInfo = instructorsInfo,
        _tags = tags,
        _registrationDate = registrationDate,
        _signature = signature,
        _opened = opened,
        _creationDate = creationDate,
        _closingDate = closingDate;

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

  // "registrationDate" field.
  String? _registrationDate;
  String get registrationDate => _registrationDate ?? '';
  set registrationDate(String? val) => _registrationDate = val;

  bool hasRegistrationDate() => _registrationDate != null;

  // "signature" field.
  String? _signature;
  String get signature => _signature ?? '';
  set signature(String? val) => _signature = val;

  bool hasSignature() => _signature != null;

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

  static TrainingStruct fromMap(Map<String, dynamic> data) => TrainingStruct(
        id: castToType<int>(data['id']),
        name: data['name'] as String?,
        description: data['description'] as String?,
        instructorsInfo: getStructList(
          data['instructorsInfo'],
          InstructorsInfoStruct.fromMap,
        ),
        tags: getStructList(
          data['tags'],
          TagsStruct.fromMap,
        ),
        registrationDate: data['registrationDate'] as String?,
        signature: data['signature'] as String?,
        opened: data['opened'] as bool?,
        creationDate: data['creation_date'] as String?,
        closingDate: data['closing_date'] as String?,
      );

  static TrainingStruct? maybeFromMap(dynamic data) =>
      data is Map ? TrainingStruct.fromMap(data.cast<String, dynamic>()) : null;

  Map<String, dynamic> toMap() => {
        'id': _id,
        'name': _name,
        'description': _description,
        'instructorsInfo': _instructorsInfo?.map((e) => e.toMap()).toList(),
        'tags': _tags?.map((e) => e.toMap()).toList(),
        'registrationDate': _registrationDate,
        'signature': _signature,
        'opened': _opened,
        'creation_date': _creationDate,
        'closing_date': _closingDate,
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
        'registrationDate': serializeParam(
          _registrationDate,
          ParamType.String,
        ),
        'signature': serializeParam(
          _signature,
          ParamType.String,
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
      }.withoutNulls;

  static TrainingStruct fromSerializableMap(Map<String, dynamic> data) =>
      TrainingStruct(
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
        registrationDate: deserializeParam(
          data['registrationDate'],
          ParamType.String,
          false,
        ),
        signature: deserializeParam(
          data['signature'],
          ParamType.String,
          false,
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
      );

  @override
  String toString() => 'TrainingStruct(${toMap()})';

  @override
  bool operator ==(Object other) {
    const listEquality = ListEquality();
    return other is TrainingStruct &&
        id == other.id &&
        name == other.name &&
        description == other.description &&
        listEquality.equals(instructorsInfo, other.instructorsInfo) &&
        listEquality.equals(tags, other.tags) &&
        registrationDate == other.registrationDate &&
        signature == other.signature &&
        opened == other.opened &&
        creationDate == other.creationDate &&
        closingDate == other.closingDate;
  }

  @override
  int get hashCode => const ListEquality().hash([
        id,
        name,
        description,
        instructorsInfo,
        tags,
        registrationDate,
        signature,
        opened,
        creationDate,
        closingDate
      ]);
}

TrainingStruct createTrainingStruct({
  int? id,
  String? name,
  String? description,
  String? registrationDate,
  String? signature,
  bool? opened,
  String? creationDate,
  String? closingDate,
}) =>
    TrainingStruct(
      id: id,
      name: name,
      description: description,
      registrationDate: registrationDate,
      signature: signature,
      opened: opened,
      creationDate: creationDate,
      closingDate: closingDate,
    );
