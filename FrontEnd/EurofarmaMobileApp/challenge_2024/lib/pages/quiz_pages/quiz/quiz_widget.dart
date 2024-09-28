import '/auth/custom_auth/auth_util.dart';
import '/backend/api_requests/api_calls.dart';
import '/backend/schema/structs/index.dart';
import '/components/radio_button_quiz/radio_button_quiz_widget.dart';
import '/flutter_flow/flutter_flow_icon_button.dart';
import '/flutter_flow/flutter_flow_theme.dart';
import '/flutter_flow/flutter_flow_util.dart';
import '/flutter_flow/flutter_flow_widgets.dart';
import 'package:flutter/material.dart';
import 'package:flutter/scheduler.dart';
import 'quiz_model.dart';
export 'quiz_model.dart';

class QuizWidget extends StatefulWidget {
  const QuizWidget({
    super.key,
    required this.quizId,
    required this.code,
    required this.title,
    required this.description,
  });

  final int? quizId;
  final String? code;
  final String? title;
  final String? description;

  @override
  State<QuizWidget> createState() => _QuizWidgetState();
}

class _QuizWidgetState extends State<QuizWidget> {
  late QuizModel _model;

  final scaffoldKey = GlobalKey<ScaffoldState>();

  @override
  void initState() {
    super.initState();
    _model = createModel(context, () => QuizModel());

    // On page load action.
    SchedulerBinding.instance.addPostFrameCallback((_) async {
      _model.apiResultju9 = await QuizGroup.findByIdCall.call(
        id: widget.quizId,
        token: currentAuthenticationToken,
      );

      if ((_model.apiResultju9?.succeeded ?? true)) {
        _model.quizInfo =
            QuizStruct.maybeFromMap((_model.apiResultju9?.jsonBody ?? ''));
        _model.numeroDeTentativas = 1;
        safeSetState(() {});
      } else {
        return;
      }
    });

    WidgetsBinding.instance.addPostFrameCallback((_) => safeSetState(() {}));
  }

  @override
  void dispose() {
    _model.dispose();

    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: () => FocusScope.of(context).unfocus(),
      child: Scaffold(
        key: scaffoldKey,
        backgroundColor: FlutterFlowTheme.of(context).primaryBackground,
        body: NestedScrollView(
          floatHeaderSlivers: true,
          headerSliverBuilder: (context, _) => [
            SliverAppBar(
              pinned: false,
              floating: false,
              backgroundColor: FlutterFlowTheme.of(context).primaryBackground,
              iconTheme: const IconThemeData(color: Color(0xFFE30C0C)),
              automaticallyImplyLeading: false,
              leading: FlutterFlowIconButton(
                borderColor: Colors.transparent,
                borderRadius: 30.0,
                borderWidth: 1.0,
                buttonSize: 48.0,
                icon: Icon(
                  Icons.arrow_back_rounded,
                  color: FlutterFlowTheme.of(context).buttons,
                  size: 28.0,
                ),
                onPressed: () async {
                  context.pop();
                },
              ),
              actions: const [],
              centerTitle: true,
              elevation: 0.0,
            )
          ],
          body: Builder(
            builder: (context) {
              return SafeArea(
                top: false,
                child: Align(
                  alignment: const AlignmentDirectional(0.0, 0.0),
                  child: Container(
                    width: double.infinity,
                    constraints: const BoxConstraints(
                      maxWidth: 770.0,
                    ),
                    decoration: BoxDecoration(
                      color: FlutterFlowTheme.of(context).primaryBackground,
                    ),
                    child: Padding(
                      padding:
                          const EdgeInsetsDirectional.fromSTEB(16.0, 0.0, 16.0, 0.0),
                      child: Column(
                        mainAxisSize: MainAxisSize.max,
                        mainAxisAlignment: MainAxisAlignment.spaceBetween,
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          Expanded(
                            child: SingleChildScrollView(
                              child: Column(
                                mainAxisSize: MainAxisSize.max,
                                crossAxisAlignment: CrossAxisAlignment.start,
                                children: [
                                  Text(
                                    valueOrDefault<String>(
                                      _model.quizInfo?.nome,
                                      'Quiz',
                                    ),
                                    style: FlutterFlowTheme.of(context)
                                        .headlineMedium
                                        .override(
                                          fontFamily: 'Outfit',
                                          fontSize: 32.0,
                                          letterSpacing: 0.0,
                                        ),
                                  ),
                                  Padding(
                                    padding: const EdgeInsetsDirectional.fromSTEB(
                                        0.0, 4.0, 16.0, 0.0),
                                    child: Text(
                                      valueOrDefault<String>(
                                        QuizGroup.findByIdCall.quizDescription(
                                          (_model.apiResultju9?.jsonBody ?? ''),
                                        ),
                                        'Descrição',
                                      ),
                                      style: FlutterFlowTheme.of(context)
                                          .labelLarge
                                          .override(
                                            fontFamily: 'Readex Pro',
                                            letterSpacing: 0.0,
                                          ),
                                    ),
                                  ),
                                  Form(
                                    key: _model.formKey,
                                    autovalidateMode: AutovalidateMode.disabled,
                                    child: Padding(
                                      padding: const EdgeInsetsDirectional.fromSTEB(
                                          0.0, 10.0, 0.0, 0.0),
                                      child: Builder(
                                        builder: (context) {
                                          final questions = _model
                                                  .quizInfo?.questions
                                                  .toList() ??
                                              [];

                                          return ListView.separated(
                                            padding: EdgeInsets.zero,
                                            primary: false,
                                            shrinkWrap: true,
                                            scrollDirection: Axis.vertical,
                                            itemCount: questions.length,
                                            separatorBuilder: (_, __) =>
                                                const SizedBox(height: 20.0),
                                            itemBuilder:
                                                (context, questionsIndex) {
                                              final questionsItem =
                                                  questions[questionsIndex];
                                              return Column(
                                                mainAxisSize: MainAxisSize.max,
                                                crossAxisAlignment:
                                                    CrossAxisAlignment.start,
                                                children: [
                                                  Text(
                                                    '${(questionsIndex + 1).toString()}.',
                                                    style: FlutterFlowTheme.of(
                                                            context)
                                                        .headlineMedium
                                                        .override(
                                                          fontFamily: 'Outfit',
                                                          color: FlutterFlowTheme
                                                                  .of(context)
                                                              .primaryText,
                                                          letterSpacing: 0.0,
                                                        ),
                                                  ),
                                                  Text(
                                                    questionsItem.question,
                                                    maxLines: 10,
                                                    style: FlutterFlowTheme.of(
                                                            context)
                                                        .headlineSmall
                                                        .override(
                                                          fontFamily: 'Outfit',
                                                          fontSize: 22.0,
                                                          letterSpacing: 0.0,
                                                        ),
                                                  ),
                                                  Container(
                                                    width: MediaQuery.sizeOf(
                                                                context)
                                                            .width *
                                                        0.9,
                                                    decoration: const BoxDecoration(),
                                                    child: wrapWithModel(
                                                      model: _model
                                                          .radioButtonQuizModels
                                                          .getModel(
                                                        questionsItem.id
                                                            .toString(),
                                                        questionsIndex,
                                                      ),
                                                      updateCallback: () =>
                                                          safeSetState(() {}),
                                                      child:
                                                          RadioButtonQuizWidget(
                                                        key: Key(
                                                          'Key72d_${questionsItem.id.toString()}',
                                                        ),
                                                        parameter1:
                                                            questionsItem
                                                                .answers
                                                                .map((e) =>
                                                                    e.answer)
                                                                .toList(),
                                                      ),
                                                    ),
                                                  ),
                                                ],
                                              );
                                            },
                                          );
                                        },
                                      ),
                                    ),
                                  ),
                                ]
                                    .divide(const SizedBox(height: 4.0))
                                    .addToEnd(const SizedBox(height: 24.0)),
                              ),
                            ),
                          ),
                          Padding(
                            padding: const EdgeInsetsDirectional.fromSTEB(
                                0.0, 12.0, 0.0, 16.0),
                            child: Row(
                              mainAxisSize: MainAxisSize.max,
                              children: [
                                Expanded(
                                  flex: 4,
                                  child: FFButtonWidget(
                                    onPressed: () async {
                                      var shouldSetState = false;
                                      if (_model.formKey.currentState == null ||
                                          !_model.formKey.currentState!
                                              .validate()) {
                                        return;
                                      }
                                      _model.apiResultajy =
                                          await QuizGroup.validateQuizCall.call(
                                        id: widget.quizId,
                                        questionIdsList:
                                            QuizGroup.findByIdCall.questionsIds(
                                          (_model.apiResultju9?.jsonBody ?? ''),
                                        ),
                                        userAnswersList: _model
                                            .radioButtonQuizModels
                                            .getValues(
                                          (m) => m.radioButtonValue,
                                        ),
                                        token: currentAuthenticationToken,
                                      );

                                      shouldSetState = true;
                                      if ((_model.apiResultajy?.succeeded ??
                                          true)) {
                                        if (QuizGroup.validateQuizCall.passed(
                                              (_model.apiResultajy?.jsonBody ??
                                                  ''),
                                            ) ==
                                            true) {
                                          context.goNamed(
                                            'QuizSummary',
                                            queryParameters: {
                                              'code': serializeParam(
                                                widget.code,
                                                ParamType.String,
                                              ),
                                              'title': serializeParam(
                                                widget.title,
                                                ParamType.String,
                                              ),
                                              'description': serializeParam(
                                                widget.description,
                                                ParamType.String,
                                              ),
                                              'quizTries': serializeParam(
                                                _model.numeroDeTentativas,
                                                ParamType.int,
                                              ),
                                              'score': serializeParam(
                                                QuizGroup.validateQuizCall
                                                    .score(
                                                  (_model.apiResultajy
                                                          ?.jsonBody ??
                                                      ''),
                                                ),
                                                ParamType.double,
                                              ),
                                            }.withoutNulls,
                                          );

                                          if (shouldSetState) {
                                            safeSetState(() {});
                                          }
                                          return;
                                        } else {
                                          _model.numeroDeTentativas =
                                              _model.numeroDeTentativas + 1;
                                          await showDialog(
                                            context: context,
                                            builder: (alertDialogContext) {
                                              return AlertDialog(
                                                title: const Text('Tente novamente'),
                                                actions: [
                                                  TextButton(
                                                    onPressed: () =>
                                                        Navigator.pop(
                                                            alertDialogContext),
                                                    child: const Text('Ok'),
                                                  ),
                                                ],
                                              );
                                            },
                                          );
                                          if (shouldSetState) {
                                            safeSetState(() {});
                                          }
                                          return;
                                        }
                                      } else {
                                        await showDialog(
                                          context: context,
                                          builder: (alertDialogContext) {
                                            return AlertDialog(
                                              title:
                                                  const Text('Erro ao enviar o quiz'),
                                              actions: [
                                                TextButton(
                                                  onPressed: () =>
                                                      Navigator.pop(
                                                          alertDialogContext),
                                                  child: const Text('Ok'),
                                                ),
                                              ],
                                            );
                                          },
                                        );
                                        if (shouldSetState) {
                                          safeSetState(() {});
                                        }
                                        return;
                                      }

                                      if (shouldSetState) safeSetState(() {});
                                    },
                                    text: FFLocalizations.of(context).getText(
                                      'vjggvduq' /* Enviar */,
                                    ),
                                    options: FFButtonOptions(
                                      width: 300.0,
                                      height: 54.0,
                                      padding: const EdgeInsets.all(0.0),
                                      iconPadding:
                                          const EdgeInsetsDirectional.fromSTEB(
                                              0.0, 0.0, 0.0, 0.0),
                                      color: FlutterFlowTheme.of(context)
                                          .secondary,
                                      textStyle: FlutterFlowTheme.of(context)
                                          .titleSmall
                                          .override(
                                            fontFamily: 'Readex Pro',
                                            color: Colors.white,
                                            letterSpacing: 0.0,
                                          ),
                                      elevation: 4.0,
                                      borderSide: const BorderSide(
                                        color: Colors.transparent,
                                        width: 1.0,
                                      ),
                                      borderRadius: BorderRadius.circular(12.0),
                                    ),
                                  ),
                                ),
                              ].divide(const SizedBox(width: 16.0)),
                            ),
                          ),
                        ],
                      ),
                    ),
                  ),
                ),
              );
            },
          ),
        ),
      ),
    );
  }
}
