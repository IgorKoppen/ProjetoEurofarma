import '/auth/custom_auth/auth_util.dart';
import '/backend/api_requests/api_calls.dart';
import '/backend/schema/structs/index.dart';
import '/components/card_instructor/card_instructor_widget.dart';
import '/components/filtro_tags/filtro_tags_widget.dart';
import '/flutter_flow/flutter_flow_autocomplete_options_list.dart';
import '/flutter_flow/flutter_flow_icon_button.dart';
import '/flutter_flow/flutter_flow_theme.dart';
import '/flutter_flow/flutter_flow_util.dart';
import 'dart:async';
import 'package:aligned_dialog/aligned_dialog.dart';
import 'package:easy_debounce/easy_debounce.dart';
import 'package:flutter/material.dart';
import 'package:flutter_spinkit/flutter_spinkit.dart';
import 'package:text_search/text_search.dart';
import 'gerenciar_treinamentos_model.dart';
export 'gerenciar_treinamentos_model.dart';

class GerenciarTreinamentosWidget extends StatefulWidget {
  const GerenciarTreinamentosWidget({super.key});

  @override
  State<GerenciarTreinamentosWidget> createState() =>
      _GerenciarTreinamentosWidgetState();
}

class _GerenciarTreinamentosWidgetState
    extends State<GerenciarTreinamentosWidget> {
  late GerenciarTreinamentosModel _model;

  final scaffoldKey = GlobalKey<ScaffoldState>();

  @override
  void initState() {
    super.initState();
    _model = createModel(context, () => GerenciarTreinamentosModel());

    _model.textController ??= TextEditingController();

    WidgetsBinding.instance.addPostFrameCallback((_) => safeSetState(() {}));
  }

  @override
  void dispose() {
    _model.dispose();

    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return FutureBuilder<ApiCallResponse>(
      future: (_model.apiRequestCompleter ??= Completer<ApiCallResponse>()
            ..complete(
                TrainingGroup.findInstructorTrainingsByInstructorIdCall.call(
              id: currentUserData?.instructorId,
              token: currentAuthenticationToken,
            )))
          .future,
      builder: (context, snapshot) {
        // Customize what your widget looks like when it's loading.
        if (!snapshot.hasData) {
          return Scaffold(
            backgroundColor: FlutterFlowTheme.of(context).primaryBackground,
            body: const Center(
              child: SizedBox(
                width: 40.0,
                height: 40.0,
                child: CircularProgressIndicator(
                  valueColor: AlwaysStoppedAnimation<Color>(
                    Colors.white,
                  ),
                ),
              ),
            ),
          );
        }
        final gerenciarTreinamentosFindInstructorTrainingsByInstructorIdResponse =
            snapshot.data!;

        return GestureDetector(
          onTap: () => FocusScope.of(context).unfocus(),
          child: Scaffold(
            key: scaffoldKey,
            backgroundColor: FlutterFlowTheme.of(context).primaryBackground,
            appBar: AppBar(
              backgroundColor: FlutterFlowTheme.of(context).primaryBackground,
              automaticallyImplyLeading: false,
              leading: FlutterFlowIconButton(
                borderColor: Colors.transparent,
                borderRadius: 30.0,
                borderWidth: 1.0,
                buttonSize: 48.0,
                icon: Icon(
                  Icons.arrow_back_rounded,
                  color: FlutterFlowTheme.of(context).buttons,
                  size: 48.0,
                ),
                onPressed: () async {
                  context.goNamed('Treinamentos');
                },
              ),
              title: Text(
                FFLocalizations.of(context).getText(
                  'nlyv3uv2' /* Treinamentos */,
                ),
                style: FlutterFlowTheme.of(context).headlineMedium.override(
                      fontFamily: 'Outfit',
                      color: FlutterFlowTheme.of(context).primaryText,
                      fontSize: 20.0,
                      letterSpacing: 0.0,
                      fontWeight: FontWeight.bold,
                    ),
              ),
              actions: const [],
              centerTitle: true,
              elevation: 0.0,
            ),
            body: SafeArea(
              top: true,
              child: Padding(
                padding: const EdgeInsetsDirectional.fromSTEB(0.0, 10.0, 0.0, 0.0),
                child: SingleChildScrollView(
                  child: Column(
                    mainAxisSize: MainAxisSize.max,
                    children: [
                      Padding(
                        padding: const EdgeInsetsDirectional.fromSTEB(
                            0.0, 10.0, 0.0, 20.0),
                        child: Row(
                          mainAxisSize: MainAxisSize.max,
                          mainAxisAlignment: MainAxisAlignment.spaceAround,
                          crossAxisAlignment: CrossAxisAlignment.center,
                          children: [
                            Expanded(
                              child: Align(
                                alignment: const AlignmentDirectional(0.0, 0.0),
                                child: Autocomplete<String>(
                                  initialValue: const TextEditingValue(),
                                  optionsBuilder: (textEditingValue) {
                                    if (textEditingValue.text == '') {
                                      return const Iterable<String>.empty();
                                    }
                                    return (gerenciarTreinamentosFindInstructorTrainingsByInstructorIdResponse
                                                .jsonBody
                                                .toList()
                                                .map<TrainingInstructorStruct?>(
                                                    TrainingInstructorStruct
                                                        .maybeFromMap)
                                                .toList()
                                            as Iterable<
                                                TrainingInstructorStruct?>)
                                        .withoutNulls
                                        .map((e) => e.name)
                                        .toList()
                                        .where((option) {
                                      final lowercaseOption =
                                          option.toLowerCase();
                                      return lowercaseOption.contains(
                                          textEditingValue.text.toLowerCase());
                                    });
                                  },
                                  optionsViewBuilder:
                                      (context, onSelected, options) {
                                    return AutocompleteOptionsList(
                                      textFieldKey: _model.textFieldKey,
                                      textController: _model.textController!,
                                      options: options.toList(),
                                      onSelected: onSelected,
                                      textStyle: FlutterFlowTheme.of(context)
                                          .bodyMedium
                                          .override(
                                            fontFamily: 'Readex Pro',
                                            letterSpacing: 0.0,
                                          ),
                                      textHighlightStyle: const TextStyle(),
                                      elevation: 4.0,
                                      optionBackgroundColor:
                                          FlutterFlowTheme.of(context)
                                              .primaryBackground,
                                      optionHighlightColor:
                                          FlutterFlowTheme.of(context)
                                              .secondaryBackground,
                                      maxHeight: 200.0,
                                    );
                                  },
                                  onSelected: (String selection) {
                                    safeSetState(() => _model
                                        .textFieldSelectedOption = selection);
                                    FocusScope.of(context).unfocus();
                                  },
                                  fieldViewBuilder: (
                                    context,
                                    textEditingController,
                                    focusNode,
                                    onEditingComplete,
                                  ) {
                                    _model.textFieldFocusNode = focusNode;

                                    _model.textController =
                                        textEditingController;
                                    return TextFormField(
                                      key: _model.textFieldKey,
                                      controller: textEditingController,
                                      focusNode: focusNode,
                                      onEditingComplete: onEditingComplete,
                                      onChanged: (_) => EasyDebounce.debounce(
                                        '_model.textController',
                                        const Duration(milliseconds: 2000),
                                        () async {
                                          safeSetState(() {
                                            _model.simpleSearchResults1 = TextSearch(
                                                    (gerenciarTreinamentosFindInstructorTrainingsByInstructorIdResponse
                                                                .jsonBody
                                                                .toList()
                                                                .map<TrainingInstructorStruct?>(
                                                                    TrainingInstructorStruct
                                                                        .maybeFromMap)
                                                                .toList()
                                                            as Iterable<
                                                                TrainingInstructorStruct?>)
                                                        .withoutNulls
                                                        .map((e) => e.name)
                                                        .toList()
                                                        .map((str) =>
                                                            TextSearchItem
                                                                .fromTerms(
                                                                    str, [str]))
                                                        .toList())
                                                .search(
                                                    _model.textController.text)
                                                .map((r) => r.object)
                                                .take(5)
                                                .toList();
                                          });
                                          _model.searchActive = 1;
                                          safeSetState(() {});
                                        },
                                      ),
                                      autofocus: false,
                                      textInputAction: TextInputAction.search,
                                      obscureText: false,
                                      decoration: InputDecoration(
                                        labelText:
                                            FFLocalizations.of(context).getText(
                                          'r4qh54be' /* Pesquisar */,
                                        ),
                                        labelStyle: FlutterFlowTheme.of(context)
                                            .bodyMedium
                                            .override(
                                              fontFamily: 'Readex Pro',
                                              fontSize: 13.0,
                                              letterSpacing: 0.0,
                                            ),
                                        hintStyle: FlutterFlowTheme.of(context)
                                            .labelMedium
                                            .override(
                                              fontFamily: 'Readex Pro',
                                              letterSpacing: 0.0,
                                            ),
                                        enabledBorder: OutlineInputBorder(
                                          borderSide: BorderSide(
                                            color: FlutterFlowTheme.of(context)
                                                .secondary,
                                            width: 2.0,
                                          ),
                                          borderRadius:
                                              BorderRadius.circular(8.0),
                                        ),
                                        focusedBorder: OutlineInputBorder(
                                          borderSide: BorderSide(
                                            color: FlutterFlowTheme.of(context)
                                                .secondary,
                                            width: 2.0,
                                          ),
                                          borderRadius:
                                              BorderRadius.circular(8.0),
                                        ),
                                        errorBorder: OutlineInputBorder(
                                          borderSide: BorderSide(
                                            color: FlutterFlowTheme.of(context)
                                                .error,
                                            width: 2.0,
                                          ),
                                          borderRadius:
                                              BorderRadius.circular(8.0),
                                        ),
                                        focusedErrorBorder: OutlineInputBorder(
                                          borderSide: BorderSide(
                                            color: FlutterFlowTheme.of(context)
                                                .error,
                                            width: 2.0,
                                          ),
                                          borderRadius:
                                              BorderRadius.circular(8.0),
                                        ),
                                        filled: true,
                                        fillColor: FlutterFlowTheme.of(context)
                                            .alternate,
                                        prefixIcon: const Icon(
                                          Icons.search_sharp,
                                        ),
                                      ),
                                      style: FlutterFlowTheme.of(context)
                                          .bodyMedium
                                          .override(
                                            fontFamily: 'Readex Pro',
                                            letterSpacing: 0.0,
                                          ),
                                      cursorColor: FlutterFlowTheme.of(context)
                                          .primaryText,
                                      validator: _model.textControllerValidator
                                          .asValidator(context),
                                    );
                                  },
                                ),
                              ),
                            ),
                            Builder(
                              builder: (context) => FlutterFlowIconButton(
                                borderRadius: 20.0,
                                borderWidth: 1.0,
                                buttonSize: 48.0,
                                icon: Icon(
                                  Icons.filter_list,
                                  color: FlutterFlowTheme.of(context).buttons,
                                  size: 28.0,
                                ),
                                onPressed: () async {
                                  showAlignedDialog(
                                    context: context,
                                    isGlobal: false,
                                    avoidOverflow: true,
                                    targetAnchor: const AlignmentDirectional(1.0, 1.0)
                                        .resolve(Directionality.of(context)),
                                    followerAnchor: const AlignmentDirectional(
                                            1.0, 1.0)
                                        .resolve(Directionality.of(context)),
                                    builder: (dialogContext) {
                                      return Material(
                                        color: Colors.transparent,
                                        child: GestureDetector(
                                          onTap: () =>
                                              FocusScope.of(dialogContext)
                                                  .unfocus(),
                                          child: FiltroTagsWidget(
                                            filter: (tagss) async {
                                              safeSetState(() {
                                                _model.simpleSearchResults2 =
                                                    TextSearch(TrainingGroup
                                                            .findInstructorTrainingsByInstructorIdCall
                                                            .tagsName(
                                                              gerenciarTreinamentosFindInstructorTrainingsByInstructorIdResponse
                                                                  .jsonBody,
                                                            )!
                                                            .map((str) =>
                                                                TextSearchItem
                                                                    .fromTerms(
                                                                        str,
                                                                        [str]))
                                                            .toList())
                                                        .search(tagss!)
                                                        .map((r) => r.object)
                                                        .toList();
                                              });
                                              _model.searchActive = 2;
                                              _model.tagFiltered = tagss;
                                              safeSetState(() {});
                                            },
                                          ),
                                        ),
                                      );
                                    },
                                  );
                                },
                              ),
                            ),
                            FlutterFlowIconButton(
                              borderColor: Colors.transparent,
                              borderRadius: 20.0,
                              borderWidth: 1.0,
                              buttonSize: 48.0,
                              fillColor: const Color(0x00FFFFFF),
                              icon: Icon(
                                Icons.close_rounded,
                                color: FlutterFlowTheme.of(context).buttons,
                                size: 28.0,
                              ),
                              onPressed: () async {
                                safeSetState(() {
                                  _model.textController?.clear();
                                });
                                _model.searchActive = 0;
                                _model.tagFiltered = null;
                                safeSetState(() {});
                              },
                            ),
                          ]
                              .divide(const SizedBox(width: 10.0))
                              .around(const SizedBox(width: 10.0)),
                        ),
                      ),
                      Builder(
                        builder: (context) {
                          if (_model.searchActive == 0) {
                            return Builder(
                              builder: (context) {
                                final trainingInstructor =
                                    (gerenciarTreinamentosFindInstructorTrainingsByInstructorIdResponse
                                                    .jsonBody
                                                    .toList()
                                                    .map<TrainingInstructorStruct?>(
                                                        TrainingInstructorStruct
                                                            .maybeFromMap)
                                                    .toList()
                                                as Iterable<
                                                    TrainingInstructorStruct?>)
                                            .withoutNulls
                                            .map((e) => e)
                                            .toList()
                                            .toList() ??
                                        [];

                                return RefreshIndicator(
                                  key: const Key('RefreshIndicator_lfbyhi70'),
                                  color: const Color(0xFF00358E),
                                  onRefresh: () async {
                                    safeSetState(() =>
                                        _model.apiRequestCompleter = null);
                                    await _model.waitForApiRequestCompleted();
                                  },
                                  child: ListView.builder(
                                    padding: EdgeInsets.zero,
                                    primary: false,
                                    shrinkWrap: true,
                                    scrollDirection: Axis.vertical,
                                    itemCount: trainingInstructor.length,
                                    itemBuilder:
                                        (context, trainingInstructorIndex) {
                                      final trainingInstructorItem =
                                          trainingInstructor[
                                              trainingInstructorIndex];
                                      return Padding(
                                        padding: const EdgeInsetsDirectional.fromSTEB(
                                            15.0, 0.0, 15.0, 0.0),
                                        child: wrapWithModel(
                                          model: _model.cardInstructorModels1
                                              .getModel(
                                            trainingInstructorIndex.toString(),
                                            trainingInstructorIndex,
                                          ),
                                          updateCallback: () =>
                                              safeSetState(() {}),
                                          child: CardInstructorWidget(
                                            key: Key(
                                              'Key0q8_${trainingInstructorIndex.toString()}',
                                            ),
                                            trainingInfo:
                                                trainingInstructorItem,
                                          ),
                                        ),
                                      );
                                    },
                                  ),
                                );
                              },
                            );
                          } else if (_model.searchActive == 1) {
                            return Builder(
                              builder: (context) {
                                final trainingInstructor =
                                    (gerenciarTreinamentosFindInstructorTrainingsByInstructorIdResponse
                                                    .jsonBody
                                                    .toList()
                                                    .map<TrainingInstructorStruct?>(
                                                        TrainingInstructorStruct
                                                            .maybeFromMap)
                                                    .toList()
                                                as Iterable<
                                                    TrainingInstructorStruct?>)
                                            .withoutNulls
                                            .where((e) => _model
                                                .simpleSearchResults1
                                                .contains(e.name))
                                            .toList()
                                            .toList() ??
                                        [];

                                return RefreshIndicator(
                                  key: const Key('RefreshIndicator_jpbzo9jx'),
                                  color: const Color(0xFF00358E),
                                  onRefresh: () async {
                                    safeSetState(() =>
                                        _model.apiRequestCompleter = null);
                                    await _model.waitForApiRequestCompleted();
                                  },
                                  child: ListView.builder(
                                    padding: EdgeInsets.zero,
                                    primary: false,
                                    shrinkWrap: true,
                                    scrollDirection: Axis.vertical,
                                    itemCount: trainingInstructor.length,
                                    itemBuilder:
                                        (context, trainingInstructorIndex) {
                                      final trainingInstructorItem =
                                          trainingInstructor[
                                              trainingInstructorIndex];
                                      return Padding(
                                        padding: const EdgeInsetsDirectional.fromSTEB(
                                            15.0, 0.0, 15.0, 0.0),
                                        child: wrapWithModel(
                                          model: _model.cardInstructorModels2
                                              .getModel(
                                            trainingInstructorIndex.toString(),
                                            trainingInstructorIndex,
                                          ),
                                          updateCallback: () =>
                                              safeSetState(() {}),
                                          child: CardInstructorWidget(
                                            key: Key(
                                              'Key9u5_${trainingInstructorIndex.toString()}',
                                            ),
                                            trainingInfo:
                                                trainingInstructorItem,
                                          ),
                                        ),
                                      );
                                    },
                                  ),
                                );
                              },
                            );
                          } else {
                            return FutureBuilder<ApiCallResponse>(
                              future: TrainingGroup.findInstructorTrainingsCall
                                  .call(
                                id: currentUserData?.instructorId,
                                tag: _model.tagFiltered,
                                token: currentAuthenticationToken,
                              ),
                              builder: (context, snapshot) {
                                // Customize what your widget looks like when it's loading.
                                if (!snapshot.hasData) {
                                  return const Center(
                                    child: SizedBox(
                                      width: 50.0,
                                      height: 50.0,
                                      child: SpinKitFadingCircle(
                                        color: Color(0xFF00358E),
                                        size: 50.0,
                                      ),
                                    ),
                                  );
                                }
                                final listViewFindInstructorTrainingsResponse =
                                    snapshot.data!;

                                return Builder(
                                  builder: (context) {
                                    final trainingInstructor =
                                        (listViewFindInstructorTrainingsResponse
                                                        .jsonBody
                                                        .toList()
                                                        .map<TrainingInstructorStruct?>(
                                                            TrainingInstructorStruct
                                                                .maybeFromMap)
                                                        .toList()
                                                    as Iterable<
                                                        TrainingInstructorStruct?>)
                                                .withoutNulls
                                                .map((e) => e)
                                                .toList()
                                                .toList() ??
                                            [];

                                    return RefreshIndicator(
                                      key: const Key('RefreshIndicator_60l8cj33'),
                                      color: const Color(0xFF00358E),
                                      onRefresh: () async {
                                        safeSetState(() =>
                                            _model.apiRequestCompleter = null);
                                        await _model
                                            .waitForApiRequestCompleted();
                                      },
                                      child: ListView.builder(
                                        padding: EdgeInsets.zero,
                                        primary: false,
                                        shrinkWrap: true,
                                        scrollDirection: Axis.vertical,
                                        itemCount: trainingInstructor.length,
                                        itemBuilder:
                                            (context, trainingInstructorIndex) {
                                          final trainingInstructorItem =
                                              trainingInstructor[
                                                  trainingInstructorIndex];
                                          return Padding(
                                            padding:
                                                const EdgeInsetsDirectional.fromSTEB(
                                                    15.0, 0.0, 15.0, 0.0),
                                            child: wrapWithModel(
                                              model: _model
                                                  .cardInstructorModels3
                                                  .getModel(
                                                trainingInstructorIndex
                                                    .toString(),
                                                trainingInstructorIndex,
                                              ),
                                              updateCallback: () =>
                                                  safeSetState(() {}),
                                              child: CardInstructorWidget(
                                                key: Key(
                                                  'Keyuot_${trainingInstructorIndex.toString()}',
                                                ),
                                                trainingInfo:
                                                    trainingInstructorItem,
                                              ),
                                            ),
                                          );
                                        },
                                      ),
                                    );
                                  },
                                );
                              },
                            );
                          }
                        },
                      ),
                    ],
                  ),
                ),
              ),
            ),
          ),
        );
      },
    );
  }
}
