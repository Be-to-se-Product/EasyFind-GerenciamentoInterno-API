package easyfind.gerenciamento.interno.controller.dto.tag;

import easyfind.gerenciamento.interno.model.Tag;

public class TagMapper {
    public static Tag of(CreateTagDto createTagDto){
        Tag tag = new Tag();
        tag.setDescricao(createTagDto.getDescricao());

        return tag;
    }

    public static ResponseTagDto of(Tag tag){
        ResponseTagDto responseTagDto = new ResponseTagDto();
        responseTagDto.setId(tag.getId());
        responseTagDto.setDescricao(tag.getDescricao());

        return responseTagDto;
    }
}
