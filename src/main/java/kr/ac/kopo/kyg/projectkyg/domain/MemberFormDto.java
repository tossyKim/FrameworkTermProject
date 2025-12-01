package kr.ac.kopo.kyg.projectkyg.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class MemberFormDto {
    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    private String memberId;
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Length(min = 8, max = 16, message = "비밀번호는 최소 8자리 이상 최대 16자리 이하로 작성해 주세요")
    private String password;
    @NotBlank(message = "성명은 필수 입력 값입니다.")
    private String memberName;
}
