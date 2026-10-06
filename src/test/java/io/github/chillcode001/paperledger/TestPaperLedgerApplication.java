package io.github.chillcode001.paperledger;

import org.springframework.boot.SpringApplication;

public class TestPaperLedgerApplication {

    public static void main(String[] args) {
        SpringApplication.from(PaperLedgerApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
