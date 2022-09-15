package edu.ufsj.lox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static edu.ufsj.lox.TokenType.*;

class Scanner {
		private final String source;
		private final List<Token> tokens = new ArrayList<Token>();
		private int start = 0;
		private int current = 0;
		private int line = 1;
		
		Scanner(String source) {
				this.source = source;
		}
		
		List<Token> scanTokens() {
				while(!isAtEnd()) {
						start = current;
						scanToken();
				}
				tokens.add(new Token(EOF, "", null, line));
				return tokens;
		}
		
		private boolean isAtEnd() {
				return current >= source.length();
		}
		
		private char advance() {
				current++;
				return source.charAt(current - 1);
		}
		
		private void addToken(TokenType type) {
				addToken(type, null);
		}
		
		private void addToken(TokenType type, Object literal) {
				String text = source.substring(start, current);
				tokens.add(new Token(type, text, literal, line));
		}

		private boolean match(char expected) {
			if(isAtEnd()) return false;
			if(source.charAt(current) != expected) return false;

			current++;
			return true;
		}
		
		private char peek() {
			if(isAtEnd()) return '\0';
			return source.charAt(current);
		}

		private void string() {
			
			while(peek() != '"' && !isAtEnd()){
				if(peek() == '\n') line++;
				advance();
			} 
			// abre " sem o perspectivo fecha "
			if(isAtEnd()) {
				Lox.error(line, "Unterminated string.");
				return;
			}
			// fecha "
			advance();
			String val = source.substring(start + 1, current - 1);
			addToken(STRING, val);
		}

		private boolean isDigit(char c) {
			return c >= '0' && c <= '9';
		}

		private void number() {
			while(isDigit(peek())) advance();
			// Procura parte fracionária
			if(peek() == '.' && isDigit(peekNext())) {
				//consome o "."
				advance();
				while(isDigit(peek())) advance()
			}
			addToken(NUMBER, Double.parseDouble(source.substring(start + 1, current - 1)));
		}

		private char peekNext() {
			if(current + 1 >= source.length())
				return '\0';
		
			return source.charAt(current + 1);

		}

		private void scanToken() {
				char c = advance();
				switch(c) {
						case '(': addToken(LEFT_PAREN); break;
						case ')': addToken(RIGHT_PAREN); break;
						case '{': addToken(LEFT_BRACE); break;
						case '}': addToken(RIGHT_BRACE); break;
						case ',': addToken(COMMA); break;
						case '.': addToken(DOT); break;
						case '-': addToken(MINUS); break;
						case '+': addToken(PLUS); break;
						case ';': addToken(SEMICOLON); break;
						case '*': addToken(STAR); break;
						case '!': addToken(match('=') ? BANG_EQUAL : BANG); break;
						case '=': addToken(match('=') ? EQUAL_EQUAL : EQUAL); break;
						case '<': addT                                                start = current;
24
                                                scanToken();
25
                                }
26
                                tokens.add(new Token(EOF, "", null, line));
27
                                return tokens;
28
                }
29
                
30
                private boolean isAtEnd() {
31
                                return current >= source.length();
32
                }
33
                
34
                private char advance() {
35
                                current++;
36
                                return source.charAt(current - 1);
37
                }
38
                
39
                private void addToken(TokenType type) {
40
                                addToken(type, null);
41
                }
42
                
43
                private void addToken(TokenType type, Object literal) {
44
                                String text = source.substring(start, current);
45
                                tokens.add(new Token(type, text, literal, line));
46
                }
47
​
48
                private boolean match(char expected) {
49
                        if(isAtEnd()) return false;
50
                        if(source.charAt(current) != expected) return false;
51
​
52
                        current++;
53
                        return true;
54
                }
55
                
56
                private char peek() {
57
                        if(isAtEnd()) return '\0';
58
                        return source.charAt(current);
59
                }
60
​
61
                private void string() {
62
                        
63
                        while(peek() != '"' && !isAtEnd()){
64
                                if(peek() == '\n') line++;
65
                                advance();
66
                        } 
67
                        // abre " sem o perspectivo fecha "
68
                        if(isAtEnd()) {
69
                                Lox.error(line, "Unterminated string.");
70
                                return ''
71
                        }
72
                        // fecha "
73
                        advance();
74
                        String val = source.substring(start + 1, current - 1);
75
                        addToken(STRING, val);
76
                }
77
​
78
                private boolean isDigit(char c) {oken(match('=') ? LESS_EQUAL : LESS); break;
						case '>': addToken(match('=') ? GREATER_EQUAL : GREATER); break;
						case ' ':
						case '\r':
						case '\t': break;
						case '"': string(); break;
						case '/':
							if(match('/')) {
								while(peek() != '\n' && !isAtEnd()) advance();
							} else {
								addToken(SLASH);
							}
							break;
						default:
							if(isDigit()){
								number();
							} else {
								Lox.error(line,  "Unexpected character.");
							}
							break;
				}
		}
}
