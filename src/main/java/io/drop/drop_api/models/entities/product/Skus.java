package io.drop.drop_api.models.entities.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "Skus")
public class Skus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;               // Identificador único da SKU (Primary Key)

    @ManyToOne
    @JoinColumn(name = "skus_id")
    private Products products;         // Identificador do produto (Foreign Key)

    @Column
    private String codeSku;         // Código único da SKU (UNIQUE, NOT NULL)

    @Column
    private String color;           // Cor do produto

    @Column
    private String size;            // Tamanho do produto

    @Column
    private BigDecimal price;       // Preço da SKU (NOT NULL)

    @Column
    private BigDecimal price_promotion;       // Preço promocional da SKU (se existir)

    @Column
    private int stock;              // Quantidade disponível em estoque (NOT NULL)

    @Column
    private boolean is_promotion;  // Indica se a SKU está em promoção

    @Column(name = "data_criacao", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime date_creation;

    @Column(name = "data_atualizacao", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime data_update;



    @PrePersist
    protected void prePersist() {
        this.date_creation = LocalDateTime.now();
        this.data_update = LocalDateTime.now();
        this.codeSku = generateCodeSku();
    }

    @PreUpdate
    protected void onUpdate() {
        this.data_update = LocalDateTime.now();
    }

    // Método para gerar automaticamente o código SKU
    public String generateCodeSku() {
        // Parte fixa do nome da loja e país
        String storeName = "DROP";
        String countryCode = "BR";

        // Data atual para o dia e mês
        LocalDateTime now = LocalDateTime.now();
        String day = String.format("%02d", now.getDayOfMonth());
        String month = String.format("%02d", now.getMonthValue());

        // Geração de 2 letras aleatórias
        String letters = generateRandomLetters(2);

        // Geração de 2 números aleatórios
        String numbers = generateRandomNumbers(2);

        // Combinação final do código SKU
        return String.format("%s%s%s%s%s%s", storeName, day, month, countryCode, letters, numbers);
    }

    // Método auxiliar para gerar letras aleatórias
    private String generateRandomLetters(int length) {
        Random random = new Random();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            // Gera uma letra aleatória de 'A' a 'Z'
            char letter = (char) (random.nextInt(26) + 'A');
            result.append(letter);
        }
        return result.toString();
    }

    // Método auxiliar para gerar números aleatórios
    private String generateRandomNumbers(int length) {
        Random random = new Random();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            // Gera um número aleatório de 0 a 9
            int number = random.nextInt(10);
            result.append(number);
        }
        return result.toString();
    }



};

