import '/backend/api_requests/api_calls.dart';
import '/backend/schema/structs/index.dart';
import '/flutter_flow/flutter_flow_util.dart';
import '/flutter_flow/form_field_controller.dart';
import 'criar_treinamento_widget.dart' show CriarTreinamentoWidget;
import 'package:flutter/material.dart';

class CriarTreinamentoModel extends FlutterFlowModel<CriarTreinamentoWidget> {
  ///  Local state fields for this page.

  List<TagStruct> tagsTreinamento = [];
  void addToTagsTreinamento(TagStruct item) => tagsTreinamento.add(item);
  void removeFromTagsTreinamento(TagStruct item) =>
      tagsTreinamento.remove(item);
  void removeAtIndexFromTagsTreinamento(int index) =>
      tagsTreinamento.removeAt(index);
  void insertAtIndexInTagsTreinamento(int index, TagStruct item) =>
      tagsTreinamento.insert(index, item);
  void updateTagsTreinamentoAtIndex(int index, Function(TagStruct) updateFn) =>
      tagsTreinamento[index] = updateFn(tagsTreinamento[index]);

  List<DepartmentStruct> departmentTreinamento = [];
  void addToDepartmentTreinamento(DepartmentStruct item) =>
      departmentTreinamento.add(item);
  void removeFromDepartmentTreinamento(DepartmentStruct item) =>
      departmentTreinamento.remove(item);
  void removeAtIndexFromDepartmentTreinamento(int index) =>
      departmentTreinamento.removeAt(index);
  void insertAtIndexInDepartmentTreinamento(int index, DepartmentStruct item) =>
      departmentTreinamento.insert(index, item);
  void updateDepartmentTreinamentoAtIndex(
          int index, Function(DepartmentStruct) updateFn) =>
      departmentTreinamento[index] = updateFn(departmentTreinamento[index]);

  List<InstructorStruct> instructor = [];
  void addToInstructor(InstructorStruct item) => instructor.add(item);
  void removeFromInstructor(InstructorStruct item) => instructor.remove(item);
  void removeAtIndexFromInstructor(int index) => instructor.removeAt(index);
  void insertAtIndexInInstructor(int index, InstructorStruct item) =>
      instructor.insert(index, item);
  void updateInstructorAtIndex(
          int index, Function(InstructorStruct) updateFn) =>
      instructor[index] = updateFn(instructor[index]);

  List<QuizStruct> quiz = [];
  void addToQuiz(QuizStruct item) => quiz.add(item);
  void removeFromQuiz(QuizStruct item) => quiz.remove(item);
  void removeAtIndexFromQuiz(int index) => quiz.removeAt(index);
  void insertAtIndexInQuiz(int index, QuizStruct item) =>
      quiz.insert(index, item);
  void updateQuizAtIndex(int index, Function(QuizStruct) updateFn) =>
      quiz[index] = updateFn(quiz[index]);

  ///  State fields for stateful widgets in this page.

  final formKey = GlobalKey<FormState>();
  // Stores action output result for [Backend Call - API (FindAll)] action in CriarTreinamento widget.
  ApiCallResponse? tags;
  // Stores action output result for [Backend Call - API (FindAllInstructors)] action in CriarTreinamento widget.
  ApiCallResponse? instructors;
  // Stores action output result for [Backend Call - API (FindAllDepartment)] action in CriarTreinamento widget.
  ApiCallResponse? department;
  // Stores action output result for [Backend Call - API (getAllQuiz)] action in CriarTreinamento widget.
  ApiCallResponse? quizCall;
  // State field(s) for nome widget.
  FocusNode? nomeFocusNode;
  TextEditingController? nomeTextController;
  String? Function(BuildContext, String?)? nomeTextControllerValidator;
  String? _nomeTextControllerValidator(BuildContext context, String? val) {
    if (val == null || val.isEmpty) {
      return FFLocalizations.of(context).getText(
        'i4luf4a9' /* Campo necessário */,
      );
    }

    return null;
  }

  DateTime? datePicked;
  // State field(s) for desc widget.
  FocusNode? descFocusNode;
  TextEditingController? descTextController;
  String? Function(BuildContext, String?)? descTextControllerValidator;
  String? _descTextControllerValidator(BuildContext context, String? val) {
    if (val == null || val.isEmpty) {
      return FFLocalizations.of(context).getText(
        '4xu80o17' /* Campo necessário */,
      );
    }

    return null;
  }

  // State field(s) for tags widget.
  List<int>? tagsValue;
  FormFieldController<List<int>>? tagsValueController;
  // State field(s) for instrutores widget.
  List<int>? instrutoresValue;
  FormFieldController<List<int>>? instrutoresValueController;
  // State field(s) for participantes widget.
  List<int>? participantesValue;
  FormFieldController<List<int>>? participantesValueController;
  // State field(s) for Checkbox widget.
  bool? checkboxValue1;
  // State field(s) for Checkbox widget.
  bool? checkboxValue2;
  // State field(s) for quizzes widget.
  int? quizzesValue;
  FormFieldController<int>? quizzesValueController;
  // Stores action output result for [Backend Call - API (CreateTrainning)] action in Button widget.
  ApiCallResponse? apiResultuoh;

  @override
  void initState(BuildContext context) {
    nomeTextControllerValidator = _nomeTextControllerValidator;
    descTextControllerValidator = _descTextControllerValidator;
  }

  @override
  void dispose() {
    nomeFocusNode?.dispose();
    nomeTextController?.dispose();

    descFocusNode?.dispose();
    descTextController?.dispose();
  }
}
