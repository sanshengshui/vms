package com.mektec.vms.view.TerminalUI;

import com.mektec.common.UID;
import com.mektec.vms.domain.Terminal;

/**
 * Created by mektec on 16-4-29.
 */
public class AddTerminalDialog extends AddTerminalDialogUI {

    public AddTerminalDialog() {
    }

    public void setTerminalDialog(Terminal terminal) {

        terminal.setTerminalId(UID.generate());
        terminal.setTerminalCode(terminalCodeText.getValue());
        terminal.setTerminalName(terminalNameText.getValue());
        terminal.setTerminalType((String) terminalTypeText.getValue());

        terminal.setDeleted(false);

    }
}
