package org.example.pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.back.PickerUserLoan;
import org.example.login.LoanUser;
import org.example.money.TakenLoan;

public class InfoAboutPays implements Command {
    private static final Logger logger = LogManager.getLogger(InfoAboutPays.class);

    private final PickerUserLoan pickUserLoan;

    public InfoAboutPays(LoanUser currentUser) {
        logger.info("Creating InfoAboutPays instance.");

        this.pickUserLoan = new PickerUserLoan(currentUser);
    }

    @Override
    public void execute() {
        logger.info("Executing InfoAboutPays command.");

        System.out.println("Enter index of the loan you want to know information about: ");
        TakenLoan takenLoan = pickUserLoan.execute();
        takenLoan.infoAboutPays();
    }
}
