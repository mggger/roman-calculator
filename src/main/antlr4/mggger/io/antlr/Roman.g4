grammar Roman;

stat : expr  ;

expr: expr '-' expr  # Sub
    | expr '+' expr  # Add
    | expr '*' expr  # Mul
    | expr '/' expr  # Div
    | '(' expr ')'   # Parens
    | roman          # RomanNum
    ;

digit: 'I' | 'II' | 'III' | 'IV'
     | 'V'
     | 'VI' | 'VII'| 'VIII'|
     | 'IX'
     |
     ;

ten: 'X' | 'XX' | 'XXX'
  | 'XL' | 'L'
  | 'LX' | 'LXX' | 'LXXX'
  | 'XC'
  ;


hundred: 'C' | 'CC' | 'CCC'
      | 'CD'
      | 'DC' | 'DCC' | 'DCCC'
      | 'CM'
      ;

thousand: 'M'
        | 'MM'
        | 'MMM'
        ;

roman: (thousand)? (hundred)? (ten)? digit;


WS: [ \t\n\r]+ -> skip;




