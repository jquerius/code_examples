-- Sum entry amounts by amount - bill payment amount
SELECT 

    ledgerEntry.D_AccountID As 'AccountID',
    SUM(CASE WHEN bp.PaymentDate < @date THEN
        COALESCE(ledgerEntry.Amount, 0) - COALESCE(bp.Amount, 0)
    ELSE 
        COALESCE(ledgerEntry.Amount, 0)
    END) As Total

FROM Entries ledgerEntry

-- Get payee account information 
JOIN Accounts accCredit ON accCredit.LedgerAccountID = ledgerEntry.C_AccountID
    AND accCredit.CustomerID = ledgerEntry.CustomerID

-- Get payer account information 
JOIN Accounts accDebit ON accDebit.LedgerAccountID = ledgerEntry.D_AccountID
    AND accDebit.CustomerID = ledgerEntry.CustomerID

-- Get any bill payments made during the statement period 
LEFT JOIN BillPayments bp ON bp.EntryID = ledgerEntry.EntryID
    AND bp.CustomerID = ledgerEntry.CustomerID
    AND bp.RemovedDate IS NULL
    AND bp.BillPaymentType = @billComponentType

-- Bills are from one account to another, can't be to the same account 
WHERE ledgerEntry.CustomerID = @adminCustDataID
    AND ledgerEntry.RemovedDate IS NULL
    AND ledgerEntry.DateEntered < @date
    AND ledgerEntry.C_AccountID != ledgerEntry.D_AccountID
    AND accCredit.Type != @guestType
    AND ((ledgerEntry.LedgerEntryType != @reversedType AND accDebit.Stakeholder = @stakeholderParam) 
            OR (ledgerEntry.LedgerEntryType = @reversedType AND accCredit.Stakeholder = @stakeholderParam))
    AND ledgerEntry.LedgerEntryType NOT IN (-- list of specific enum types)

-- Group results by account ID so that we gather sum values, not individuals 
GROUP BY ledgerEntry.D_AccountID
ORDER BY AccountID;
