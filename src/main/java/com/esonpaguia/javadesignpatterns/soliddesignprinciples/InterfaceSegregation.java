package com.esonpaguia.javadesignpatterns.soliddesignprinciples;

import java.nio.channels.MulticastChannel;

/**
 * You should not put into your interface more than what the client is expected to implement.
 * Segregation means putting apart or putting into separate categories/locations.
 */
class InterfaceSegregationDemo {

}


class Document {

}

interface Machine {
    void print(Document d) throws Exception;
    void fax(Document d) throws Exception;
    void scan(Document d) throws Exception;
}

class MultiFunctionPrinter implements Machine {
    @Override
    public void print(Document d) {

    }

    @Override
    public void fax(Document d) {

    }

    @Override
    public void scan(Document d) {

    }
}

class OldPrinter implements Machine {
    @Override
    public void print(Document d) {

    }

    @Override
    public void fax(Document d) throws Exception {
        throw new Exception();
    }

    @Override
    public void scan(Document d) throws Exception {
        throw new Exception();
    }
}

// Solution 1
interface Printer {
    void print(Document d);
}

interface Scanner {
    void scan(Document d);
}

interface Fax {
    void fax(Document d);
}

class JustAPrinter implements Printer {
    @Override
    public void print(Document d) {
        
    }
}

class Photocopier implements Printer, Scanner {
    @Override
    public void print(Document d) {
        
    }

    @Override
    public void scan(Document d) {

    }
}

interface MultiFunctionDevice extends Printer, Scanner {
    
}

class MultiFunctionMachine implements MultiFunctionDevice {

    private Printer printer;
    private Scanner scanner;

    public MultiFunctionMachine(Printer printer, Scanner scanner) {
        this.printer = printer;
        this.scanner = scanner;
    }

    @Override
    public void print(Document d) {
        printer.print(d);
    }

    @Override
    public void scan(Document d) {
        scanner.scan(d);
    }
}