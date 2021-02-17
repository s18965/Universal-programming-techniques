package zad1;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Operacja{
    public BigDecimal pomnoz(BigDecimal b1, BigDecimal b2) {
        return b1.multiply(b2).setScale(7,RoundingMode.HALF_UP).stripTrailingZeros();
    }
    public BigDecimal odejmij(BigDecimal b1, BigDecimal b2) {
        return b1.subtract(b2).setScale(7,RoundingMode.HALF_UP).stripTrailingZeros();
    }
    public BigDecimal podziel(BigDecimal b1, BigDecimal b2) {
        return b1.divide(b2, 7,RoundingMode.HALF_UP).stripTrailingZeros();
    }
    public BigDecimal dodaj(BigDecimal b1, BigDecimal b2) {
        return b1.add(b2).setScale(7,RoundingMode.HALF_UP).stripTrailingZeros();
    }}
