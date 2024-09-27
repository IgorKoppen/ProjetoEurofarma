import 'package:flutter/material.dart';
import 'package:flutter/foundation.dart';
import 'package:shared_preferences/shared_preferences.dart';

const _kLocaleStorageKey = '__locale_key__';

class FFLocalizations {
  FFLocalizations(this.locale);

  final Locale locale;

  static FFLocalizations of(BuildContext context) =>
      Localizations.of<FFLocalizations>(context, FFLocalizations)!;

  static List<String> languages() => ['pt', 'es', 'en'];

  static late SharedPreferences _prefs;
  static Future initialize() async =>
      _prefs = await SharedPreferences.getInstance();
  static Future storeLocale(String locale) =>
      _prefs.setString(_kLocaleStorageKey, locale);
  static Locale? getStoredLocale() {
    final locale = _prefs.getString(_kLocaleStorageKey);
    return locale != null && locale.isNotEmpty ? createLocale(locale) : null;
  }

  String get languageCode => locale.toString();
  String? get languageShortCode =>
      _languagesWithShortCode.contains(locale.toString())
          ? '${locale.toString()}_short'
          : null;
  int get languageIndex => languages().contains(languageCode)
      ? languages().indexOf(languageCode)
      : 0;

  String getText(String key) =>
      (kTranslationsMap[key] ?? {})[locale.toString()] ?? '';

  String getVariableText({
    String? ptText = '',
    String? esText = '',
    String? enText = '',
  }) =>
      [ptText, esText, enText][languageIndex] ?? '';

  static const Set<String> _languagesWithShortCode = {
    'ar',
    'az',
    'ca',
    'cs',
    'da',
    'de',
    'dv',
    'en',
    'es',
    'et',
    'fi',
    'fr',
    'gr',
    'he',
    'hi',
    'hu',
    'it',
    'km',
    'ku',
    'mn',
    'ms',
    'no',
    'pt',
    'ro',
    'ru',
    'rw',
    'sv',
    'th',
    'uk',
    'vi',
  };
}

class FFLocalizationsDelegate extends LocalizationsDelegate<FFLocalizations> {
  const FFLocalizationsDelegate();

  @override
  bool isSupported(Locale locale) {
    final language = locale.toString();
    return FFLocalizations.languages().contains(
      language.endsWith('_')
          ? language.substring(0, language.length - 1)
          : language,
    );
  }

  @override
  Future<FFLocalizations> load(Locale locale) =>
      SynchronousFuture<FFLocalizations>(FFLocalizations(locale));

  @override
  bool shouldReload(FFLocalizationsDelegate old) => false;
}

Locale createLocale(String language) => language.contains('_')
    ? Locale.fromSubtags(
        languageCode: language.split('_').first,
        scriptCode: language.split('_').last,
      )
    : Locale(language);

final kTranslationsMap = <Map<String, Map<String, String>>>[
  // LoginPage
  {
    'slfswldw': {
      'pt': 'Bem-Vindo(a)',
      'en': 'Welcome',
      'es': 'Bienvenido',
    },
    'm7fg2l4w': {
      'pt': 'Usuário',
      'en': 'User',
      'es': 'Usuario',
    },
    '84iq8nia': {
      'pt': 'Senha',
      'en': 'Password',
      'es': 'Contraseña',
    },
    'lnvvcrpd': {
      'pt': 'Entrar',
      'en': 'Login',
      'es': 'Conectarse',
    },
    'q080dpz0': {
      'pt': 'Nome do usuário é obrigatório!',
      'en': 'User name is required!',
      'es': '¡Nombre de usuario obligatorio!',
    },
    'fwgueg3y': {
      'pt': 'Please choose an option from the dropdown',
      'en': 'Please choose an option from the dropdown',
      'es': 'Por favor elija una opción del menú desplegable',
    },
    '84fdn4we': {
      'pt': 'Senha requerida!',
      'en': 'Password required!',
      'es': '¡Se requiere contraseña!',
    },
    'b7ws0e4t': {
      'pt': 'Please choose an option from the dropdown',
      'en': 'Please choose an option from the dropdown',
      'es': 'Por favor elija una opción del menú desplegable',
    },
    'kj0gl6tr': {
      'pt': 'Home',
      'en': 'Home',
      'es': 'Principal',
    },
  },
  // ChatbotPage
  {
    'htha27q1': {
      'pt': 'EuroChat',
      'en': 'EuroChat',
      'es': 'EuroChat',
    },
    'vtiszzqb': {
      'pt': 'Mensagem para Eurinho',
      'en': 'Message for Eurinho',
      'es': 'Mensaje a Eurinho',
    },
    'kfmjjuwj': {
      'pt': 'É necessário uma mensagem',
      'en': 'A message is required',
      'es': 'Se requiere un mensaje',
    },
    '08umpjfs': {
      'pt': 'É necessário no minimo de 10 caracteres',
      'en': 'A minimum of 10 characters is required',
      'es': 'Se requiere un mínimo de 10 caracteres',
    },
    'kbr6jnbg': {
      'pt': 'Messagem máxima excedida',
      'en': 'Maximum message exceeded',
      'es': 'Mensaje máximo excedido',
    },
    '9egf7i0f': {
      'pt': 'Please choose an option from the dropdown',
      'en': 'Please choose an option from the dropdown',
      'es': 'Por favor elija una opción del menú desplegable',
    },
    'gnd3g5ff': {
      'pt': 'Home',
      'en': 'Home',
      'es': 'Principal',
    },
  },
  // Settings
  {
    'ofgiuy4d': {
      'pt': 'Senha',
      'en': 'Password',
      'es': 'Contraseña',
    },
    'cp569y76': {
      'pt': 'Tema',
      'en': 'Theme',
      'es': 'Tema',
    },
    '86qb41tq': {
      'pt': 'Idioma',
      'en': 'Language',
      'es': 'Idioma',
    },
    'wyqklq44': {
      'pt': 'Celular',
      'en': 'Cell phone',
      'es': 'Teléfono Móvil',
    },
    'hn0k68bq': {
      'pt': 'Sair',
      'en': 'Exit',
      'es': 'Salir',
    },
    'nds9scve': {
      'pt': 'Perfil',
      'en': 'Profile',
      'es': 'Perfil',
    },
  },
  // HomePage
  {
    'igxj2qd3': {
      'pt': 'Bem vindo!',
      'en': 'Welcome!',
      'es': '¡Bienvenido!',
    },
    'df6isxjt': {
      'pt': 'Garanta já sua presença nos treinamentos',
      'en': 'Guarantee your presence at the training sessions now',
      'es': 'Garantiza ya su presencia en las sesiones de formación',
    },
    'emovz70x': {
      'pt': 'Ações rápidas',
      'en': 'Quick actions',
      'es': 'Acciones rápidas',
    },
    '9kovm0ip': {
      'pt': 'Validar treinamentos',
      'en': 'Validate trainings',
      'es': 'Validar formación',
    },
    'a01j3lqe': {
      'pt': 'Valide sua presença no seu próximo treinamento com a Eurofarma',
      'en':
          'Validate your attendance at your next training session with Eurofarma',
      'es':
          'Valide su participación en su próxima sesión de formación con Eurofarma',
    },
    '3l00xnlp': {
      'pt': 'EuroChat',
      'en': 'EuroChat',
      'es': 'EuroChat',
    },
    'hecclv80': {
      'pt': 'Faça perguntas sobre nossa empresa',
      'en': 'Ask questions about our company',
      'es': 'Haga preguntas sobre nuestra empresa',
    },
    'wf6msogs': {
      'pt': 'Questionários',
      'en': 'Quizzes',
      'es': 'Cuestionarios',
    },
    'nd6hwmck': {
      'pt': 'Crie e gerencie os questionários',
      'en': 'Create and manage quizzes',
      'es': 'Crear y gestionar cuestionarios',
    },
    'snuny0ow': {
      'pt': 'Recentes',
      'en': 'Recent',
      'es': 'Reciente',
    },
    '79q6ja12': {
      'pt': 'Home',
      'en': 'Home',
      'es': 'Principal',
    },
  },
  // AlterarSenha
  {
    '9fo6acz7': {
      'pt': 'Insira sua senha atual e sua nova senha',
      'en': 'Enter your current password and your new password',
      'es': 'Ingrese su contraseña actual y su nueva contraseña',
    },
    'ztcpjjr7': {
      'pt': 'Senha atual',
      'en': 'Current password',
      'es': 'Contraseña actual',
    },
    'olkxkpva': {
      'pt': 'Nova senha',
      'en': 'New password',
      'es': 'Nueva contraseña',
    },
    '3fik0iiq': {
      'pt': 'Confirmar nova senha',
      'en': 'Confirm new password',
      'es': 'Confirmar nueva contraseña',
    },
    'l78ky16w': {
      'pt': 'As senhas não coincidem',
      'en': 'The passwords don\'t match',
      'es': 'Las contraseñas no coinciden',
    },
    'dg90ckhn': {
      'pt': 'Alterar',
      'en': 'Change',
      'es': 'Cambiar',
    },
    'slfoaf42': {
      'pt':
          'A senha precisa ter pelo menos 8 dígitos.\nPelo menos 1 letra maiúscula.\nPelo menos 1 letra caractere especial.\nPelo menos 1 letra número.',
      'en':
          'The password must have at least 8 digits.\nAt least 1 capital letter.\nAt least 1 special character.\nAt least 1 letter number.',
      'es':
          'La contraseña debe tener al menos 8 dígitos.\nAl menos 1 letra mayúscula.\nAl menos 1 letra de carácter especial.\nAl menos 1 número de letra.',
    },
    '06ze91fu': {
      'pt': 'Campo necessário',
      'en': 'Required field',
      'es': 'Campo requerido',
    },
    '0h8wt9ls': {
      'pt': 'Please choose an option from the dropdown',
      'en': '',
      'es': '',
    },
    'g5ggpx3n': {
      'pt': 'Campo necessário',
      'en': 'Required field',
      'es': 'Campo requerido',
    },
    '6em6eoyv': {
      'pt': 'Pelo menos 8 letras',
      'en': 'At least 8 letters',
      'es': 'Al menos 8 letras',
    },
    'onwiyanl': {
      'pt': 'A senha precisa seguir as regras',
      'en': 'The password must follow the rules',
      'es': 'La contraseña debe seguir las reglas.',
    },
    '3ko6pdf5': {
      'pt': 'Please choose an option from the dropdown',
      'en': 'Please choose an option from the dropdown',
      'es': 'Por favor elija una opción del menú desplegable',
    },
    '1fmglrgl': {
      'pt': 'Campo necessário',
      'en': 'Required field',
      'es': 'Campo requerido',
    },
    'xxxsj6jh': {
      'pt': 'Pelo menos 8 letras',
      'en': 'At least 8 letters',
      'es': 'Al menos 8 letras',
    },
    '3zl0lp6e': {
      'pt': 'A senha precisa seguir as regras',
      'en': 'The password must follow the rules',
      'es': 'La contraseña debe seguir las reglas.',
    },
    'oys32aa6': {
      'pt': 'Please choose an option from the dropdown',
      'en': '',
      'es': '',
    },
    'icdf0suy': {
      'pt': 'Alterar Senha',
      'en': 'Change Password',
      'es': 'Cambiar contraseña',
    },
    'x3t86dwb': {
      'pt': 'Home',
      'en': 'Home',
      'es': 'Principal',
    },
  },
  // Treinamentos
  {
    'mwnb05g3': {
      'pt': 'Validar treinamentos',
      'en': 'Validate trainings',
      'es': 'Validar formación',
    },
    '1gd8dub8': {
      'pt': 'Valide sua presença no seu próximo treinamento',
      'en': 'Validate your attendance at your next training',
      'es': 'Valide su participación en su próxima sesión de formación',
    },
    '9nf75gv9': {
      'pt': 'Gerenciar treinamentos',
      'en': 'Manage training',
      'es': 'Gestionar la formación',
    },
    'tl44hb21': {
      'pt': 'Crie um novo treinamento e veja seu histórico de criação',
      'en': 'Create a new training session and view its creation history',
      'es': 'Crear una nueva formación y ver su historial de creación.',
    },
    'g1rmj4y0': {
      'pt': 'Seus treinamentos',
      'en': 'Your trainings',
      'es': 'Sus formaciones',
    },
    'ir951gub': {
      'pt': 'Pesquisar',
      'en': 'Search',
      'es': 'Buscar',
    },
    'l5unhe3g': {
      'pt': 'Option 1',
      'en': 'Option 1',
      'es': 'Opción 1',
    },
    '09zqum0f': {
      'pt': 'Treinamentos',
      'en': 'Trainings',
      'es': 'Formación',
    },
    'lwjkiz5l': {
      'pt': 'Treinamentos',
      'en': 'Trainings',
      'es': 'Formación',
    },
  },
  // GerenciarTreinamentos
  {
    'r4qh54be': {
      'pt': 'Pesquisar',
      'en': 'Search',
      'es': 'Buscar',
    },
    'spi5pohx': {
      'pt': 'Option 1',
      'en': 'Option 1',
      'es': 'Opción 1',
    },
    'nlyv3uv2': {
      'pt': 'Treinamentos',
      'en': 'Trainings',
      'es': 'Formación',
    },
    'vnxmxv62': {
      'pt': 'Treinamentos',
      'en': 'Trainings',
      'es': 'Formación',
    },
  },
  // CriarTreinamento
  {
    'swycdcrn': {
      'pt': 'Complete os dados do novo treinamento',
      'en': 'Complete the new training data',
      'es': 'Completar los nuevos datos de formación',
    },
    '195rfm3g': {
      'pt': 'Nome do treinamento',
      'en': 'Training name',
      'es': 'Nombre de la formacion',
    },
    'bhj4l3tu': {
      'pt': 'Data de conclusão',
      'en': 'Completion date',
      'es': 'Fecha de finalización',
    },
    'vchbo0ws': {
      'pt': 'Descrição',
      'en': 'Description',
      'es': 'Descripción',
    },
    'wny8du66': {
      'pt': 'Etiquetas',
      'en': 'Tags',
      'es': 'Etiquetas',
    },
    '0jnsec4a': {
      'pt': 'option 1',
      'en': 'option 1',
      'es': 'opción 1',
    },
    'i03hkf3z': {
      'pt': 'Selecione as tags',
      'en': 'Select the tags',
      'es': 'Seleccionar etiquetas',
    },
    'xcj7rdu6': {
      'pt': 'Pesquise',
      'en': 'Search',
      'es': 'Buscar',
    },
    'qtndm2bb': {
      'pt': 'Instrutores',
      'en': 'Instructors',
      'es': 'Instructores',
    },
    'abjt6ki1': {
      'pt': 'Option 1',
      'en': 'Option 1',
      'es': 'Opción 1',
    },
    'uj0klpy2': {
      'pt': 'Selecione os instrutores',
      'en': 'Select the instructors',
      'es': 'Seleccionar instructores',
    },
    'w16nokfm': {
      'pt': 'Pesquise',
      'en': 'Search',
      'es': 'Buscar',
    },
    'l0pxlfys': {
      'pt': 'Participantes',
      'en': 'Participants',
      'es': 'Participantes',
    },
    'm1pxdent': {
      'pt': 'Option 1',
      'en': 'Option 1',
      'es': 'Opción 1',
    },
    'u4i0374s': {
      'pt': 'Selecione os participantes',
      'en': 'Select the participants',
      'es': 'Seleccionar participantes',
    },
    'e3aodf64': {
      'pt': 'Pesquise',
      'en': 'Search',
      'es': 'Buscar',
    },
    't0f2vmog': {
      'pt': 'Deseja notificar os participantes?',
      'en': 'Would you like to notify the participants?',
      'es': '¿Desea notificarlo a los participantes?',
    },
    'xu7824mu': {
      'pt': 'Incluir Quiz?\n(Necessário criar anteriormente)',
      'en': 'Include Quiz?\n(Need to be created beforehand)',
      'es': '¿Incluir cuestionario?\n(Se requiere creación previa)',
    },
    'y697d4wg': {
      'pt': 'Quizzes',
      'en': 'Quizzes',
      'es': 'Cuestionarios',
    },
    'kp0k27cf': {
      'pt': 'Option 1',
      'en': '',
      'es': '',
    },
    'p49l3vy8': {
      'pt': 'Selecione o quiz',
      'en': 'Select a quiz',
      'es': 'Seleccione un cuestionario',
    },
    'wm33lm7c': {
      'pt': 'Pesquise',
      'en': 'Search',
      'es': 'Buscar en',
    },
    'fqvfyi8y': {
      'pt': 'Criar',
      'en': 'Create',
      'es': 'Crear',
    },
    'i4luf4a9': {
      'pt': 'Campo necessário',
      'en': 'Field is required',
      'es': 'El campo es obligatorio',
    },
    '9wlz21lo': {
      'pt': 'Please choose an option from the dropdown',
      'en': 'Please choose an option from the dropdown',
      'es': 'Por favor elija una opción del menú desplegable',
    },
    '4xu80o17': {
      'pt': 'Campo necessário',
      'en': 'Field is required',
      'es': 'El campo es obligatorio',
    },
    '89jwywf6': {
      'pt': 'Please choose an option from the dropdown',
      'en': 'Please choose an option from the dropdown',
      'es': 'Por favor elija una opción del menú desplegable',
    },
    'b8hys5jx': {
      'pt': 'Criar Treinamento',
      'en': 'Create Training',
      'es': 'Crear formación',
    },
    'gu919kab': {
      'pt': 'Treinamentos',
      'en': 'Trainings',
      'es': ' Formación',
    },
  },
  // Assinatura
  {
    'jsot39ue': {
      'pt': 'Assinatura',
      'en': 'Signature',
      'es': 'Firma',
    },
    '31t77jbm': {
      'pt': 'Enviar',
      'en': 'Send',
      'es': 'Enviar',
    },
    'bud9i09k': {
      'pt': 'Treinamentos',
      'en': 'Training',
      'es': 'Formación',
    },
  },
  // NewTag
  {
    '6xedzu7s': {
      'pt': 'Home',
      'en': 'Home',
      'es': 'Principal',
    },
  },
  // EditarTreinamento
  {
    'e7t7viu7': {
      'pt': 'Complete os dados do novo treinamento',
      'en': 'Complete the new training data',
      'es': 'Completar los nuevos datos de formación',
    },
    'hrir5cn8': {
      'pt': 'Nome da sala',
      'en': 'Room name',
      'es': 'Nombre de la sala',
    },
    'o5tddmno': {
      'pt': 'Data de conclusão',
      'en': 'Completion date',
      'es': 'Fecha de finalización',
    },
    'xfz98rod': {
      'pt': 'Descrição',
      'en': 'Description',
      'es': 'Descripción',
    },
    'qll0wwhc': {
      'pt': 'Etiquetas',
      'en': 'Tags',
      'es': 'Etiquetas',
    },
    'ev5arlvj': {
      'pt': 'option 1',
      'en': 'option 1',
      'es': 'opción 1',
    },
    'jdpbzoaj': {
      'pt': 'Selecione as tags',
      'en': 'Select the tags',
      'es': 'Seleccionar etiquetas',
    },
    't4m0y4tj': {
      'pt': 'Pesquise',
      'en': 'Search',
      'es': 'Buscar',
    },
    'e70voxur': {
      'pt': 'Instrutores',
      'en': 'Instructors',
      'es': 'Instructores',
    },
    'rmjyk8y9': {
      'pt': 'Option 1',
      'en': 'Option 1',
      'es': 'Opción 1',
    },
    '3z59ktyv': {
      'pt': 'Selecione os instrutores',
      'en': 'Select the instructors',
      'es': 'Seleccionar instructores',
    },
    'z85ajsml': {
      'pt': 'Pesquise',
      'en': 'Search',
      'es': 'Buscar',
    },
    'kiqv6mgv': {
      'pt': 'Participantes',
      'en': 'Participants',
      'es': 'Participantes',
    },
    'kgh27jyn': {
      'pt': 'Option 1',
      'en': '',
      'es': '',
    },
    'jjfzusq4': {
      'pt': 'Selecione os participantes',
      'en': 'Select the participants',
      'es': 'Seleccionar a los participantes',
    },
    'qlqm8slw': {
      'pt': 'Pesquise',
      'en': 'Search',
      'es': 'Buscar en',
    },
    'wmqevjj3': {
      'pt': 'Deseja notificar os participantes?',
      'en': 'Would you like to notify the participants?',
      'es': '¿Desea notificarlo a los participantes?',
    },
    'ak0qyxzv': {
      'pt': 'Incluir Quiz?\n(Necessário criar anteriormente)',
      'en': 'Include Quiz?\n(Need to be created beforehand)',
      'es': '¿Incluir cuestionario?\n(Se requiere creación previa)',
    },
    'k20kigns': {
      'pt': 'Quizzes',
      'en': 'Quizzes',
      'es': 'Cuestionarios',
    },
    'g22o9y4f': {
      'pt': 'Selecione o quiz',
      'en': 'Select the quiz',
      'es': 'Seleccione el cuestionario',
    },
    '0l0hu7n7': {
      'pt': 'Pesquise',
      'en': 'Search',
      'es': 'Buscar en',
    },
    'jqxwg1w1': {
      'pt': 'Editar',
      'en': 'Edit',
      'es': 'Editar',
    },
    '9kf6ntp2': {
      'pt': 'Editar Treinamento',
      'en': 'Editar Treinamento',
      'es': 'Editar la formación',
    },
    'a02alivg': {
      'pt': 'Treinamentos',
      'en': 'Trainings',
      'es': 'Formación',
    },
  },
  // RegistrarInfoTreinamento
  {
    'dtt1guen': {
      'pt':
          'Confira os dados da sala e insira a senha para confirmar o treinamento.',
      'en':
          'Check the room details and enter the password to confirm the training.',
      'es':
          'Consulta los datos de la sala e introduce la contraseña para confirmar la formación.',
    },
    'pu9wlbaj': {
      'pt': 'Nome da sala:',
      'en': 'Room name:',
      'es': 'Nombre de la sala:',
    },
    'apt6t44v': {
      'pt': 'Descrição:',
      'en': 'Description:',
      'es': 'Descripción:',
    },
    '6vnnefwt': {
      'pt': 'Senha',
      'en': 'Password',
      'es': 'Contraseña',
    },
    'rrww919h': {
      'pt': 'Senha da sala',
      'en': 'Room password',
      'es': 'Contraseña de la sala',
    },
    'h7op2301': {
      'pt': 'Confirmar',
      'en': 'Confirm',
      'es': 'Confirmar',
    },
    '8m36vgxy': {
      'pt': 'Senha é necessária!',
      'en': 'Password required!',
      'es': '¡Se requiere contraseña!',
    },
    '1a683220': {
      'pt': 'Mínimo de characteres é 10',
      'en': 'Minimum number of characters is 10',
      'es': 'Los caracteres mínimos son 10.',
    },
    'uy8x988r': {
      'pt': 'Máximo de characteres é 10',
      'en': 'Maximum number of characters is 10',
      'es': 'El máximo de caracteres es 10.',
    },
    'ohf5irne': {
      'pt': 'Please choose an option from the dropdown',
      'en': 'Please choose an option from the dropdown',
      'es': 'Por favor elija una opción del menú desplegable',
    },
    'u5218fyt': {
      'pt': 'Confirmar Treinamento',
      'en': 'Confirm Training',
      'es': 'Confirmar Formación',
    },
    'lrwoev7t': {
      'pt': 'Treinamentos',
      'en': 'Trainings',
      'es': 'Formación',
    },
  },
  // AlterarCelular
  {
    'ik54pi7n': {
      'pt': 'Insira sua senha atual da conta e seu novo número',
      'en': 'Enter your current account password and your new number',
      'es': 'Ingrese la contraseña de su cuenta actual y su nuevo número',
    },
    'm7q2k47j': {
      'pt': 'Senha atual',
      'en': 'Current password',
      'es': 'Contraseña actual',
    },
    'cmy85zqi': {
      'pt': 'Novo celular',
      'en': 'New number',
      'es': 'Nuevo celular',
    },
    'ng4whe56': {
      'pt': '',
      'en': '',
      'es': '',
    },
    'ctw0a23a': {
      'pt': 'Ex: 55 (11) 99999-9999',
      'en': 'Ex: (11) 99999-9999',
      'es': 'Ej: (11) 99999-9999',
    },
    'duuzkdoq': {
      'pt': 'Confirmar',
      'en': 'Confirm',
      'es': 'Confirmar',
    },
    '3onv3vrs': {
      'pt': 'Campo necessário',
      'en': 'Required field',
      'es': 'Campo requerido',
    },
    'o59fuo3y': {
      'pt': 'Please choose an option from the dropdown',
      'en': 'Please choose an option from the dropdown',
      'es': 'Por favor elija una opción del menú desplegable',
    },
    'fe2gwqj0': {
      'pt': 'Campo necessário',
      'en': 'Required field',
      'es': 'Campo requerido',
    },
    'nfrks0jt': {
      'pt': 'São necessários 13 digitos',
      'en': '13 digits required',
      'es': 'Necesita 13 dígitos',
    },
    'sxx10j62': {
      'pt': 'São necessários 13 digitos',
      'en': '13 digits required',
      'es': 'Necesita 13 dígitos',
    },
    'j5hrhvly': {
      'pt': 'A senha precisa seguir as regras',
      'en': 'The password must follow the rules',
      'es': 'La contraseña debe seguir las reglas',
    },
    'ox5h7bx0': {
      'pt': 'Please choose an option from the dropdown',
      'en': 'Please choose an option from the dropdown',
      'es': 'Please choose an option from the dropdown',
    },
    'ug8nui3p': {
      'pt': 'Atualizar Celular',
      'en': 'Update Number',
      'es': 'Actualizar móvil',
    },
    'u7nxnj8m': {
      'pt': 'Home',
      'en': 'Home',
      'es': 'Principal',
    },
  },
  // EditInfo
  {
    'bxabtene': {
      'pt': 'Home',
      'en': 'Home',
      'es': 'Principal',
    },
  },
  // PresenceList
  {
    'xu415gus': {
      'pt': 'Presentes: ',
      'en': 'Attendees:',
      'es': 'Participantes:',
    },
    '1oql4i5p': {
      'pt': 'Home',
      'en': 'Home',
      'es': 'Principal',
    },
  },
  // Idioma
  {
    '8nfz8d08': {
      'pt': 'Escolha o idioma de sua preferência',
      'en': 'Choose your preferred language',
      'es': 'Elige tu idioma preferido',
    },
    'd8yynbme': {
      'pt': 'Português',
      'en': 'Português',
      'es': 'Português',
    },
    '3fm0wcb4': {
      'pt': 'Español',
      'en': 'Español',
      'es': 'Español',
    },
    'lye7vswx': {
      'pt': 'English',
      'en': 'English',
      'es': 'English',
    },
    '9ixjt8ah': {
      'pt': 'Idioma',
      'en': 'Language',
      'es': 'Idioma',
    },
    'imapqggn': {
      'pt': 'Home',
      'en': 'Home',
      'es': 'Principal',
    },
  },
  // CreateQuiz
  {
    'pgadlla0': {
      'pt': 'Criar Questionário',
      'en': 'Create Questionnaire',
      'es': 'Crear cuestionario',
    },
    'oy8ho9om': {
      'pt': 'Informe o nome do questionário para criar',
      'en': 'Enter the name of the questionnaire to create',
      'es': 'Introduzca el nombre del cuestionario que desea crear',
    },
    'vworrxab': {
      'pt': 'Nome do questionário',
      'en': 'Questionnaire name',
      'es': 'Nombre del cuestionario',
    },
    'sdzwhrhz': {
      'pt': 'Descrição',
      'en': 'Description',
      'es': 'Descripción',
    },
    '462i0mp7': {
      'pt': 'Número de questões',
      'en': 'Number of questions',
      'es': 'Número de preguntas',
    },
    'cw52vami': {
      'pt': 'Nota mínima',
      'en': 'Minimum score',
      'es': 'Puntuación  mínima',
    },
    '2thp5ihk': {
      'pt': 'Criar',
      'en': 'Create',
      'es': 'Crear',
    },
    'gt6j9y7i': {
      'pt': 'Campo requerido',
      'en': 'Password required!',
      'es': '¡Se requiere contraseña!',
    },
    'rxtmmx1v': {
      'pt': 'Minimo 5 caracteres',
      'en': 'Minimum 5 characters',
      'es': 'Mínimo 5 caracteres',
    },
    '8iodt992': {
      'pt': 'Please choose an option from the dropdown',
      'en': 'Please choose an option from the dropdown',
      'es': 'Por favor elija una opción del menú desplegable',
    },
    'w6bc8qjg': {
      'pt': 'Campo requerido',
      'en': 'Password required!',
      'es': '¡Se requiere contraseña!',
    },
    '3b2bqdy5': {
      'pt': 'Minimo 5 caracteres',
      'en': 'Minimum 5 characters',
      'es': 'Mínimo 5 caracteres',
    },
    '9x4cor7d': {
      'pt': 'Please choose an option from the dropdown',
      'en': 'Please choose an option from the dropdown',
      'es': 'Por favor elija una opción del menú desplegable',
    },
    '6y6xcu47': {
      'pt': 'Dasda',
      'en': 'Home',
      'es': 'Principal',
    },
  },
  // CreateQuestion
  {
    '6zm931lh': {
      'pt': 'Criar Questão',
      'en': 'Create Question',
      'es': 'Crear pregunta',
    },
    'q6yopsv5': {
      'pt': 'Preencha abaixo as informações para criar a questão',
      'en': 'Fill in the information below to create the question',
      'es': 'Rellene la siguiente información para crear la pregunta',
    },
    't9w8qkvt': {
      'pt': 'Pergunta',
      'en': 'Question',
      'es': 'Pregunta',
    },
    'p5dx38d7': {
      'pt': 'Respostas',
      'en': 'Answers',
      'es': 'Respuestas',
    },
    '0k5z5bc0': {
      'pt': 'Campo necessário',
      'en': 'Password required!',
      'es': '¡Se requiere contraseña!',
    },
    '5k2hn6l8': {
      'pt': 'Mínimo 5 caracteres',
      'en': 'Minimum 5 characters',
      'es': 'Mínimo 5 caracteres',
    },
    't85nw6iv': {
      'pt': 'Máximo 400 caracteres',
      'en': 'Maximum 400 characters',
      'es': 'Máximo 400 caracteres',
    },
    '34cdrqq3': {
      'pt': 'Please choose an option from the dropdown',
      'en': 'Please choose an option from the dropdown',
      'es': 'Por favor elija una opción del menú desplegable',
    },
    '0yk2gxdl': {
      'pt': 'Resposta',
      'en': 'Answer',
      'es': 'Respuesta',
    },
    'ohyg4wxe': {
      'pt': 'Please add an option name...',
      'en': '',
      'es': '',
    },
    '4qut2tlx': {
      'pt': 'Please choose an option from the dropdown',
      'en': '',
      'es': '',
    },
    'q3yufsrw': {
      'pt': 'Selecione a questão correta abaixo',
      'en': 'Select the correct question below',
      'es': 'Seleccione la pregunta correcta',
    },
    'rh8ea2v2': {
      'pt': 'Resposta correta',
      'en': 'Correct Answer',
      'es': 'Respuesta correcta',
    },
    'eaeiiixp': {
      'pt': 'Salvar e sair',
      'en': 'Save and exit',
      'es': 'Guardar y salir',
    },
    'w12fc598': {
      'pt': 'Add Questão',
      'en': 'Add Question',
      'es': 'Add pregunta',
    },
    'jn47us6b': {
      'pt': 'Home',
      'en': 'Home',
      'es': 'Inicio',
    },
  },
  // Quiz
  {
    'vjggvduq': {
      'pt': 'Enviar',
      'en': 'Send',
      'es': 'Enviar',
    },
    '4prqh9zh': {
      'pt': 'Home',
      'en': 'Home',
      'es': 'Principal',
    },
  },
  // QuizDetails
  {
    'ckap7b7k': {
      'pt': 'Escolha um questionário para editar',
      'en': 'Choose a quiz to edit',
      'es': 'Elija un cuestionario para editar',
    },
    '7fqa8dg9': {
      'pt': 'Excluir',
      'en': 'Delete',
      'es': 'Borrar',
    },
    'jhm0kgen': {
      'pt': 'Home',
      'en': 'Home',
      'es': 'Principal',
    },
  },
  // EditQuestion
  {
    '23dqdyf1': {
      'pt': 'Editar Questão',
      'en': 'Edit Question',
      'es': 'Editar pregunta',
    },
    'l75kcy5i': {
      'pt': 'Preencha abaixo as informações para editar a questão',
      'en': 'Fill in the information below to edit the question',
      'es': 'Rellene la siguiente información para editar la pregunta',
    },
    '3d4ny417': {
      'pt': 'Pergunta',
      'en': 'Question',
      'es': 'Pregunta',
    },
    'qgzwbyk4': {
      'pt': '',
      'en': '',
      'es': '',
    },
    'txi9xe4l': {
      'pt': 'Respostas',
      'en': 'Answers',
      'es': 'Respuestas',
    },
    'vdyylbst': {
      'pt': 'Campo necessário',
      'en': 'Required field',
      'es': 'Campo obligatorio',
    },
    'x399lrux': {
      'pt': 'Mínimo 10 caracteres',
      'en': 'Minimum 10 characters',
      'es': 'Mínimo 10 caracteres',
    },
    'mi5aj4rp': {
      'pt': 'Máximo 100 caracteres',
      'en': 'Maximum 100 characters',
      'es': 'Máximo 100 caracteres',
    },
    '7hzh55cd': {
      'pt': 'Please choose an option from the dropdown',
      'en': '',
      'es': '',
    },
    'w8w9txrz': {
      'pt': 'Campo necessário',
      'en': 'Required field',
      'es': 'Campo obligatorio',
    },
    'k5znp6ai': {
      'pt': 'Máximo 400 caracteres',
      'en': 'Maximum 400 characters',
      'es': 'Máximo 400 caracteres',
    },
    'gcq36jj1': {
      'pt': 'Please choose an option from the dropdown',
      'en': '',
      'es': '',
    },
    'tmfemveu': {
      'pt': 'Campo necessário',
      'en': 'Required field',
      'es': 'Campo obligatorio',
    },
    'bjbslbvz': {
      'pt': 'Please choose an option from the dropdown',
      'en': '',
      'es': '',
    },
    '95iqpbcl': {
      'pt': 'Resposta',
      'en': 'Answer',
      'es': 'Respuesta',
    },
    'frmk2o24': {
      'pt': 'Adicione uma opção',
      'en': 'Add an option',
      'es': 'Agregar una opción',
    },
    'l6316yoe': {
      'pt': 'Please choose an option from the dropdown',
      'en': '',
      'es': '',
    },
    '3ie7cnp0': {
      'pt': 'Selecione a questão correta abaixo',
      'en': 'Select the correct question below',
      'es': 'Seleccione la pregunta correcta',
    },
    'me5s5ce2': {
      'pt': 'Resposta correta',
      'en': 'Correct Answer',
      'es': 'Respuesta correcta',
    },
    '65bb9ckb': {
      'pt': 'Salvar alterações',
      'en': 'Save changes',
      'es': 'Guardar cambios',
    },
    'h48z0x2a': {
      'pt': 'Home',
      'en': 'Home',
      'es': 'Principal',
    },
  },
  // ShowQuiz
  {
    'ckkz8wxz': {
      'pt': 'Questionários',
      'en': 'Questionnaires',
      'es': 'Cuestionarios',
    },
    '1h8wyczo': {
      'pt': 'Clique no questionário para modificá-lo',
      'en': 'Click on the questionnaire to modify it',
      'es': 'Haga clic en el cuestionario para modificarlo',
    },
    'mctdz8dr': {
      'pt': 'Criar novo',
      'en': 'Create new',
      'es': 'Crear nuevo',
    },
    '01uvst2r': {
      'pt': 'Home',
      'en': 'Home',
      'es': 'Principal',
    },
  },
  // QuizSummary
  {
    'zlbok7z1': {
      'pt': 'Parabéns!',
      'en': 'Congratulations!',
      'es': '¡Felicidades!',
    },
    '1ave9eg2': {
      'pt': 'Você atingiu a pountuação necessária',
      'en': 'You have reached the required score',
      'es': 'Has alcanzado la puntuación requerida',
    },
    'ss5bhvu7': {
      'pt': 'Continuar',
      'en': 'Continue',
      'es': 'Continúe en',
    },
    '5tv2czx3': {
      'pt': 'Home',
      'en': 'Home',
      'es': 'Principal',
    },
  },
  // CreateNewQuestion
  {
    'uxz4amdt': {
      'pt': 'Criar Questão',
      'en': 'Create Question',
      'es': 'Crear pregunta',
    },
    'm1tanomq': {
      'pt': 'Preencha abaixo as informações para criar a questão',
      'en': 'Fill in the information below to create the question',
      'es': 'Rellene la siguiente información para crear la pregunta',
    },
    'tdshdb6c': {
      'pt': 'Pergunta',
      'en': 'Question',
      'es': 'Pregunta',
    },
    'siu6ukl5': {
      'pt': 'Respostas',
      'en': 'Answers',
      'es': 'Respuestas',
    },
    'idckte2v': {
      'pt': 'Campo necessário',
      'en': 'Required field',
      'es': 'Campo obligatorio',
    },
    'qr9hj20e': {
      'pt': 'Mínimo 10 caracteres',
      'en': 'Minimum 10 characters',
      'es': 'Mínimo 10 caracteres',
    },
    'mstr35y0': {
      'pt': 'Máximo 100 caracteres',
      'en': 'Maximum 100 characters',
      'es': 'Máximo 100 caracteres',
    },
    '1myqxzb9': {
      'pt': 'Please choose an option from the dropdown',
      'en': '',
      'es': '',
    },
    '2yfdzy41': {
      'pt': 'Campo necessário',
      'en': 'Required field',
      'es': 'Campo obligatorio',
    },
    'gmac8bru': {
      'pt': 'Máximo 400 caracteres',
      'en': 'Maximum 400 characters',
      'es': 'Máximo 400 caracteres',
    },
    '1uuf6pgk': {
      'pt': 'Please choose an option from the dropdown',
      'en': '',
      'es': '',
    },
    'iqjkxc59': {
      'pt': 'Campo necessário',
      'en': 'Required field',
      'es': 'Campo obligatorio',
    },
    'srmlc7fj': {
      'pt': 'Please choose an option from the dropdown',
      'en': '',
      'es': '',
    },
    'xv0cwnld': {
      'pt': 'Resposta',
      'en': 'Answer',
      'es': 'Respuesta',
    },
    'mw5fscxy': {
      'pt': 'Adicione uma resposta',
      'en': 'Add an answer',
      'es': 'Adicione una question',
    },
    '2op1ob4e': {
      'pt': 'Please choose an option from the dropdown',
      'en': '',
      'es': '',
    },
    '98cemi0l': {
      'pt': 'Selecione a questão correta abaixo',
      'en': 'Select the correct question below',
      'es': 'Seleccione la pregunta correcta',
    },
    '2fkrt2dm': {
      'pt': 'Resposta correta',
      'en': 'Correct answer',
      'es': 'Respuesta correcta',
    },
    'xjuw8j76': {
      'pt': 'Salvar alterações',
      'en': 'Save changes',
      'es': 'Guardar cambios',
    },
    'qyjzbh7i': {
      'pt': 'Home',
      'en': 'Home',
      'es': 'Principal',
    },
  },
  // Themelightdark
  {
    'ocjjkc50': {
      'pt': 'Atualizar tema',
      'en': 'Update theme',
      'es': 'Actualizar tema',
    },
    'uzhm4qw7': {
      'pt':
          'Atualize o tema do seu aplicativo selecionando uma das opções abaixo.',
      'en':
          'Update your application\'s theme by selecting one of the options below.',
      'es':
          'Actualice el tema de su aplicación seleccionando una de las siguientes opciones.',
    },
    '7bf90vxg': {
      'pt': 'Escuro',
      'en': 'Dark',
      'es': 'Oscuro',
    },
    'dofqmy3z': {
      'pt': 'Claro',
      'en': 'Light',
      'es': 'Claro ',
    },
    'en0zg956': {
      'pt': 'Cancelar',
      'en': 'Cancel',
      'es': 'Cancelar',
    },
    'ey24xrij': {
      'pt': 'Salvar',
      'en': 'Save',
      'es': 'Guardar',
    },
  },
  // RegistrarTreinamento
  {
    'vj5w0k5h': {
      'pt': 'Registre seu novo treinamento',
      'en': 'Register your new training',
      'es': 'Registra tu nueva formación',
    },
    'frfvxdrt': {
      'pt': 'Escaneie o QR Code ou insira o código da sala',
      'en': 'Scan the QR Code or enter the room code',
      'es': 'Escanea el Código QR o introduce el código de la sala',
    },
    '5a5cwm7p': {
      'pt': 'Scannear QR Code',
      'en': 'Scan QR Code',
      'es': 'Escanear código QR',
    },
    'fpm5fqwu': {
      'pt': 'Cancelar',
      'en': 'Cancel',
      'es': 'Cancelar',
    },
    'e7vfy5nt': {
      'pt': 'Código da sala',
      'en': 'Room code',
      'es': 'Código de la sala',
    },
    '8o4a7yni': {
      'pt': 'O código é obrigatório',
      'en': 'The code is required',
      'es': 'El código es obligatorio.',
    },
    'nu0vx73w': {
      'pt': 'O codigo possui 10 caracteres',
      'en': 'The code has 10 characters',
      'es': 'El código tiene 10 caracteres.',
    },
    '46zntdsl': {
      'pt': 'O codigo possui 10 caracteres',
      'en': 'The code has 10 characters',
      'es': 'El código tiene 10 caracteres.',
    },
    'qor7vm2q': {
      'pt': 'Please choose an option from the dropdown',
      'en': 'Please choose an option from the dropdown',
      'es': 'Por favor elija una opción del menú desplegable',
    },
    'ponmbpg3': {
      'pt': 'A senha é obrigatória',
      'en': 'Password required',
      'es': 'La contraseña es obligatoria',
    },
    '5otibik2': {
      'pt': 'A senha possui 10 caracteres',
      'en': 'The password has 10 characters',
      'es': 'La contraseña tiene 10 caracteres.',
    },
    '8xl2twdw': {
      'pt': 'A senha possui 10 caracteres',
      'en': 'The password has 10 characters',
      'es': 'La contraseña tiene 10 caracteres.',
    },
    'va71u35m': {
      'pt': 'Please choose an option from the dropdown',
      'en': 'Please choose an option from the dropdown',
      'es': 'Por favor elija una opción del menú desplegable',
    },
    'wxzp34fk': {
      'pt': 'Próximo',
      'en': 'Next',
      'es': 'Próximo',
    },
  },
  // DocumentDetail
  {
    'p8miw14d': {
      'pt': 'Texto extraido do documento',
      'en': 'Text extracted from the document',
      'es': 'Texto extraído del documento.',
    },
  },
  // CardTraining
  {
    'grlwqfyu': {
      'pt': 'Concluido',
      'en': 'Completed',
      'es': 'Completado',
    },
  },
  // OpcoesTreinamento
  {
    '45k0d79h': {
      'pt': 'Opções',
      'en': 'Options',
      'es': 'Opciones',
    },
    'wzwzbjhq': {
      'pt': 'Criar novo treinamento',
      'en': 'Create new training',
      'es': 'Crear nueva formación',
    },
    'l6fgkh6x': {
      'pt': 'Gerenciar treinamentos',
      'en': 'Manage training',
      'es': 'Gestionar la formación',
    },
    '5hujvltz': {
      'pt': 'Cancelar',
      'en': 'Cancel',
      'es': 'Cancelar',
    },
  },
  // TreinamentoInfo
  {
    'alfkk7iq': {
      'pt': 'Etiquetas',
      'en': 'Tags',
      'es': 'Etiquetas',
    },
    'yvnwgt1x': {
      'pt': 'Data de conclusão',
      'en': 'Completion date',
      'es': 'Fecha de finalización',
    },
    'fozdzepg': {
      'pt': 'Responsáveis',
      'en': 'Responsible',
      'es': 'Responsable',
    },
    '074if5u7': {
      'pt': 'Descrição',
      'en': 'Description',
      'es': 'Descripción',
    },
  },
  // UserInfo
  {
    'sj712k9i': {
      'pt': 'RE:',
      'en': 'ER:',
      'es': 'RE:',
    },
    'tmk8vi5v': {
      'pt': 'Departamento:',
      'en': 'Department:',
      'es': 'Departamento:',
    },
    '7ie6oa58': {
      'pt': 'Cargo:',
      'en': 'Position:',
      'es': 'Cargo:',
    },
    'jq4rbyh7': {
      'pt': 'Celular:',
      'en': 'Cell phone:',
      'es': 'Teléfono móvil:',
    },
  },
  // CriarTag
  {
    'vkn00aza': {
      'pt': 'CRIAR NOVA TAG',
      'en': 'CREATE NEW TAG',
      'es': 'CREAR NUEVA ETIQUETA',
    },
    '1m5n979v': {
      'pt': 'Escolha o nome e a cor da nova tag',
      'en': 'Choose the name and color of the new tag',
      'es': 'Elige el nombre y color de la nueva etiqueta.',
    },
    'i1hj0ogz': {
      'pt': 'Nome da etiqueta',
      'en': 'Label name',
      'es': 'Nombre de etiqueta',
    },
    'y0w0yepg': {
      'pt': 'Campo necessário',
      'en': 'Required field',
      'es': 'Campo requerido',
    },
    '8gw4w2dq': {
      'pt': 'A tag deve conter no mínimo 3 caracteres',
      'en': 'The tag must contain at least 3 characters',
      'es': 'La etiqueta debe contener al menos 3 caracteres.',
    },
    'u11t7nkn': {
      'pt': 'A tag deve conter no máximo 20 caracteres',
      'en': 'The tag must contain a maximum of 20 characters',
      'es': 'La etiqueta debe contener un máximo de 20 caracteres.',
    },
    'zmagfg7d': {
      'pt': 'A tag só pode conter letras números',
      'en': 'The tag can only contain letters and numbers',
      'es': 'La etiqueta sólo puede contener letras y números',
    },
    'exp3mnze': {
      'pt': 'Please choose an option from the dropdown',
      'en': 'Please choose an option from the dropdown',
      'es': 'Por favor elija una opción del menú desplegable',
    },
    'bmwu1h9l': {
      'pt': 'Escolher cor da tag',
      'en': 'Choose tag color',
      'es': 'Elige el color de la etiqueta',
    },
    'nnsvu2qt': {
      'pt': 'Voltar',
      'en': 'Back',
      'es': 'Volver',
    },
    'xy61jeei': {
      'pt': 'Criar',
      'en': 'Create',
      'es': 'Crear',
    },
  },
  // TreinamentoEdit
  {
    'e3s955jt': {
      'pt': 'Etiquetas',
      'en': 'Tags',
      'es': 'Etiquetas',
    },
    'kt5u1aak': {
      'pt': 'Data de expiração',
      'en': 'Expiration date',
      'es': 'Fecha de expiración',
    },
    'zfl9djfs': {
      'pt': 'Responsáveis',
      'en': 'Responsible',
      'es': 'Responsable',
    },
    'tyvf03nr': {
      'pt': 'Descrição',
      'en': 'Description',
      'es': 'Descripción',
    },
    'gjtp2e31': {
      'pt': 'Código do treinamento',
      'en': 'Training code',
      'es': 'Código de formación',
    },
    'rbml3laz': {
      'pt': 'Senha',
      'en': 'Password',
      'es': 'Contraseña',
    },
    'ybcdtvgq': {
      'pt': '',
      'en': '',
      'es': '',
    },
    'puo3zvvo': {
      'pt': 'QR Code da sala',
      'en': 'Room QR Code',
      'es': 'Código QR de la sala',
    },
    'vs8iknjm': {
      'pt': '(Clique no código para ver o QR Code)',
      'en': '(Click on the code to see the QR Code)',
      'es': '(Haga clic en el código para ver el código QR)',
    },
    '3x1336qa': {
      'pt': 'Compartilhar',
      'en': 'Share',
      'es': 'Compartir',
    },
    't9wgcd2q': {
      'pt': 'Ver lista de presença',
      'en': 'See attendance list',
      'es': 'Ver lista de asistencia',
    },
    'eurkc7xp': {
      'pt': 'Cancelar treinamento',
      'en': 'Cancel training',
      'es': 'Cancelar formación',
    },
  },
  // PopupQrCode
  {
    'xzvnnlg1': {
      'pt': 'Tire uma print',
      'en': 'Take a print',
      'es': 'Tome una impresión',
    },
    'wh90empl': {
      'pt': 'Ou clique aqui para copiar',
      'en': 'Or click here to copy',
      'es': 'O haga clic aquí para copiar',
    },
    'ex4ydtqf': {
      'pt': 'Fechar',
      'en': 'Close',
      'es': 'Cerrar',
    },
  },
  // FiltroTags
  {
    't59vktjh': {
      'pt': 'Filtre por tags',
      'en': 'Filter by tags',
      'es': 'Filtrar por etiquetas',
    },
    'jmcb9mos': {
      'pt': 'option 1',
      'en': 'option 1',
      'es': 'opción 1',
    },
    '3ba58vgo': {
      'pt': 'Escolha uma tag',
      'en': 'Choose a tag',
      'es': 'Elige una etiqueta',
    },
    'lyh1ju10': {
      'pt': '',
      'en': '',
      'es': '',
    },
    'qgvmyey5': {
      'pt': 'Voltar',
      'en': 'Back',
      'es': 'Volver',
    },
    '4vrq6u84': {
      'pt': 'Filtrar',
      'en': 'Filter',
      'es': 'Filtrar',
    },
  },
  // ConfirmDeleteTraining
  {
    '5x9smq52': {
      'pt': 'Tem certeza que deseja excluir?',
      'en': 'Are you sure you want to delete it?',
      'es': '¿Seguro que quieres borrarlo?',
    },
    'u7loinm5': {
      'pt': 'Excluir',
      'en': 'Delete',
      'es': 'Borrar',
    },
    'gw3p80a2': {
      'pt': 'Cancelar',
      'en': 'Cancel',
      'es': 'Cancelar',
    },
  },
  // ConfirmDeleteQuestion
  {
    'lrnvgrnn': {
      'pt': 'Tem certeza que deseja excluir?',
      'en': 'Are you sure you want to delete it?',
      'es': '¿Seguro que quieres borrarlo?',
    },
    'iq1e5tx3': {
      'pt': 'Excluir',
      'en': 'Delete',
      'es': 'Borrar',
    },
    's1a30veu': {
      'pt': 'Cancelar',
      'en': 'Cancel',
      'es': 'Cancelar',
    },
  },
  // ConfirmDeleteQuiz
  {
    'jigzd6w0': {
      'pt': 'Tem certeza que deseja excluir?',
      'en': '',
      'es': '',
    },
    'v84y3hxd': {
      'pt': 'Excluir',
      'en': '',
      'es': '',
    },
    'p8iw5d8l': {
      'pt': 'Cancelar',
      'en': '',
      'es': '',
    },
  },
  // Miscellaneous
  {
    'rw21m6g7': {
      'pt':
          'Para escanear o QR code, este aplicativo requer permissão para acessar a câmera.',
      'en': '',
      'es': '',
    },
    'iln7ypid': {
      'pt': '',
      'en': '',
      'es': '',
    },
    'xx3uydak': {
      'pt': '',
      'en': '',
      'es': '',
    },
    'nh0bxjuq': {
      'pt': '',
      'en': '',
      'es': '',
    },
    'p32ulgj3': {
      'pt': '',
      'en': '',
      'es': '',
    },
    '7e4ts12y': {
      'pt': '',
      'en': '',
      'es': '',
    },
    'cbqhpxa9': {
      'pt': '',
      'en': '',
      'es': '',
    },
    'eygq9zkv': {
      'pt': '',
      'en': '',
      'es': '',
    },
    '9v74l8js': {
      'pt': '',
      'en': '',
      'es': '',
    },
    'lp4lf3wn': {
      'pt': '',
      'en': '',
      'es': '',
    },
    'xqug8uex': {
      'pt': '',
      'en': '',
      'es': '',
    },
    'ca8xjd58': {
      'pt': '',
      'en': '',
      'es': '',
    },
    'wqqe0c45': {
      'pt': '',
      'en': '',
      'es': '',
    },
    '83r2lpqo': {
      'pt': '',
      'en': '',
      'es': '',
    },
    'ac22kt7l': {
      'pt': '',
      'en': '',
      'es': '',
    },
    'w909nc09': {
      'pt': '',
      'en': '',
      'es': '',
    },
    'd6r9brzi': {
      'pt': '',
      'en': '',
      'es': '',
    },
    'cgu7kcdo': {
      'pt': '',
      'en': '',
      'es': '',
    },
    'hslk2u5h': {
      'pt': '',
      'en': '',
      'es': '',
    },
    'a27zg47f': {
      'pt': '',
      'en': '',
      'es': '',
    },
    'wcnwdhpu': {
      'pt': '',
      'en': '',
      'es': '',
    },
    'hsku0b34': {
      'pt': '',
      'en': '',
      'es': '',
    },
    '54ibxp10': {
      'pt': '',
      'en': '',
      'es': '',
    },
    'qnm2z08y': {
      'pt': '',
      'en': '',
      'es': '',
    },
    '5id3th0h': {
      'pt': '',
      'en': '',
      'es': '',
    },
    '5256ulu2': {
      'pt': '',
      'en': '',
      'es': '',
    },
  },
].reduce((a, b) => a..addAll(b));
