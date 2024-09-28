import '/backend/api_requests/api_calls.dart';
import '/backend/schema/structs/index.dart';
import '/flutter_flow/flutter_flow_util.dart';
import '/flutter_flow/form_field_controller.dart';
import 'filtro_tags_widget.dart' show FiltroTagsWidget;
import 'package:flutter/material.dart';

class FiltroTagsModel extends FlutterFlowModel<FiltroTagsWidget> {
  ///  Local state fields for this component.

  List<TagStruct> tags = [];
  void addToTags(TagStruct item) => tags.add(item);
  void removeFromTags(TagStruct item) => tags.remove(item);
  void removeAtIndexFromTags(int index) => tags.removeAt(index);
  void insertAtIndexInTags(int index, TagStruct item) =>
      tags.insert(index, item);
  void updateTagsAtIndex(int index, Function(TagStruct) updateFn) =>
      tags[index] = updateFn(tags[index]);

  ///  State fields for stateful widgets in this component.

  // Stores action output result for [Backend Call - API (FindAll)] action in FiltroTags widget.
  ApiCallResponse? apiResult2a8;
  // State field(s) for tags widget.
  String? tagsValue;
  FormFieldController<String>? tagsValueController;

  @override
  void initState(BuildContext context) {}

  @override
  void dispose() {}
}
