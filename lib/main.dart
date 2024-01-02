import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() {
  runApp(const MainApp());
}

class MainApp extends StatelessWidget {
  const MainApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Source Engine App',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.orange),
        useMaterial3: true,
      ),
      home: const IndexPage(title: 'Source Engine App'),
    );
  }
}

class IndexPage extends StatefulWidget {
  const IndexPage({super.key, required this.title});

  final String title;

  @override
  State<IndexPage> createState() {
    return _IndexPage();
  }
}

class _IndexPage extends State<IndexPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Column(
        children: [
          const SizedBox(
            height: 100,
          ),
          buildStartButton(),
        ],
      ),
    );
  }

  Widget buildStartButton() {
    return Align(
      child: ElevatedButton(
        onPressed: () async {
          const platform = const MethodChannel('runIntermediaryActivity');
          String returnValue = await platform.invokeMethod('/storage/emulated/0/半条命2/Half-Life 2/Half-Life 2/');
          print("返回值为：" + returnValue);
        },
        child: Text('START'),
      ),
    );
  }
}
