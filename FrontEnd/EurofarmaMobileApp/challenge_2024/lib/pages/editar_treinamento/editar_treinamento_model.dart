import '/backend/api_requests/api_calls.dart';
import '/backend/schema/structs/index.dart';
import '/flutter_flow/flutter_flow_util.dart';
import '/flutter_flow/form_field_controller.dart';
import 'editar_treinamento_widget.dart' show EditarTreinamentoWidget;
import 'package:flutter/material.dart';

class EditarTreinamentoModel extends FlutterFlowModel<EditarTreinamentoWidget> {
  ///  Local state fields for this page.

  List<TagsStruct> editarTags = [];
  void addToEditarTags(TagsStruct item) => editarTags.add(item);
  void removeFromEditarTags(TagsStruct item) => editarTags.remove(item);
  void removeAtIndexFromEditarTags(int index) => editarTags.removeAt(index);
  void insertAtIndexInEditarTags(int index, TagsStruct item) =>
      editarTags.insert(index, item);
  void updateEditarTagsAtIndex(int index, Function(TagsStruct) updateFn) =>
      editarTags[index] = updateFn(editarTags[index]);

  List<InstructorStruct> editarInstrutores = [];
  void addToEditarInstrutores(InstructorStruct item) =>
      editarInstrutores.add(item);
  void removeFromEditarInstrutores(InstructorStruct item) =>
      editarInstrutores.remove(item);
  void removeAtIndexFromEditarInstrutores(int index) =>
      editarInstrutores.removeAt(index);
  void insertAtIndexInEditarInstrutores(int index, InstructorStruct item) =>
      editarInstrutores.insert(index, item);
  void updateEditarInstrutoresAtIndex(
          int index, Function(InstructorStruct) updateFn) =>
      editarInstrutores[index] = updateFn(editarInstrutores[index]);

  DateTime? newDate;

  List<QuizStruct> quizList = [];
  void addToQuizList(QuizStruct item) => quizList.add(item);
  void removeFromQuizList(QuizStruct item) => quizList.remove(item);
  void removeAtIndexFromQuizList(int index) => quizList.removeAt(index);
  void insertAtIndexInQuizList(int index, QuizStruct item) =>
      quizList.insert(index, item);
  void updateQuizListAtIndex(int index, Function(QuizStruct) updateFn) =>
      quizList[index] = updateFn(quizList[index]);

  List<DepartmentStruct> listaDepartamentos = [];
  void addToListaDepartamentos(DepartmentStruct item) =>
      listaDepartamentos.add(item);
  void removeFromListaDepartamentos(DepartmentStruct item) =>
      listaDepartamentos.remove(item);
  void removeAtIndexFromListaDepartamentos(int index) =>
      listaDepartamentos.removeAt(index);
  void insertAtIndexInListaDepartamentos(int index, DepartmentStruct item) =>
      listaDepartamentos.insert(index, item);
  void updateListaDepartamentosAtIndex(
          int index, Function(DepartmentStruct) updateFn) =>
      listaDepartamentos[index] = updateFn(listaDepartamentos[index]);

  ///  State fields for stateful widgets in this page.

  final formKey = GlobalKey<FormState>();
  // Stores action output result for [Backend Call - API (FindAllInstructors)] action in EditarTreinamento widget.
  ApiCallResponse? findAllInstructors;
  // Stores action output result for [Backend Call - API (getAllQuiz)] action in EditarTreinamento widget.
  ApiCallResponse? apiResult81h;
  // Stores action output result for [Backend Call - API (FindAllDepartment)] action in EditarTreinamento widget.
  ApiCallResponse? apiResultfhr;
  // Stores action output result for [Backend Call - API (FindAll)] action in EditarTreinamento widget.
  ApiCallResponse? findAllTags;
  // State field(s) for nome widget.
  FocusNode? nomeFocusNode1;
  TextEditingController? nomeTextController1;
  String? Function(BuildContext, String?)? nomeTextController1Validator;
  // State field(s) for nome widget.
  FocusNode? nomeFocusNode2;
  TextEditingController? nomeTextController2;
  String? Function(BuildContext, String?)? nomeTextController2Validator;
  DateTime? datePicked;
  // State field(s) for desc widget.
  FocusNode? descFocusNode;
  TextEditingController? descTextController;
  String? Function(BuildContext, String?)? descTextControllerValidator;
  // State field(s) for tags widget.
  List<int>? tagsValue;
  FormFieldController<List<int>>? tagsValueController;
  // State field(s) for instrutores widget.
  List<int>? instrutoresValue;
  FormFieldController<List<int>>? instrutoresValueController;
  // State field(s) for participantes widget.
  List<int>? participantesValue;
  FormFieldController<List<int>>? participantesValueController;
  // State field(s) for participantesCheckbox widget.
  bool? participantesCheckboxValue;
  // State field(s) for quizCheck widget.
  bool? quizCheckValue;
  // State field(s) for quizzes widget.
  int? quizzesValue;
  FormFieldController<int>? quizzesValueController;
  // Stores action output result for [Backend Call - API (EditTraining)] action in Button widget.
  ApiCallResponse? apiResultuoh;

  @override
  void initState(BuildContext context) {}

  @override
  void dispose() {
    nomeFocusNode1?.dispose();
    nomeTextController1?.dispose();

    nomeFocusNode2?.dispose();
    nomeTextController2?.dispose();

    descFocusNode?.dispose();
    descTextController?.dispose();
  }
}
