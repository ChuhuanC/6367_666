package org.pitest.mutationtest.engine.gregor.mutators.TCZ;

import java.util.HashMap;
import java.util.Map;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.gregor.AbstractJumpMutator;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;

public enum RORLEMutator implements MethodMutatorFactory {

    LE_MUTATOR;

    @Override
    public MethodVisitor create(final MutationContext context,
        final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
      return new LEMethodVisitor(this, context, methodVisitor);
    }

    @Override
    public String getGloballyUniqueId() {
      return this.getClass().getName();
    }

    @Override
    public String getName() {
      return name();
    }

  }

class LEMethodVisitor extends AbstractJumpMutator {

    private static final String                     DESCRIPTION = "negated conditional";
    private static final Map<Integer, Substitution> MUTATIONS   = new HashMap<>();

    static {
      MUTATIONS.put(Opcodes.IFEQ, new Substitution(Opcodes.IFLE, DESCRIPTION));
      MUTATIONS.put(Opcodes.IFNE, new Substitution(Opcodes.IFLE, DESCRIPTION));
      MUTATIONS.put(Opcodes.IFGE, new Substitution(Opcodes.IFLE, DESCRIPTION));
      MUTATIONS.put(Opcodes.IFGT, new Substitution(Opcodes.IFLE, DESCRIPTION));
      MUTATIONS.put(Opcodes.IFLT, new Substitution(Opcodes.IFLE, DESCRIPTION));
      
      MUTATIONS.put(Opcodes.IF_ICMPNE, new Substitution(Opcodes.IF_ICMPLE,
              DESCRIPTION));
      MUTATIONS.put(Opcodes.IF_ICMPEQ, new Substitution(Opcodes.IF_ICMPLE,
              DESCRIPTION));
      MUTATIONS.put(Opcodes.IF_ICMPGE, new Substitution(Opcodes.IF_ICMPLE,
              DESCRIPTION));
      MUTATIONS.put(Opcodes.IF_ICMPGT, new Substitution(Opcodes.IF_ICMPLE,
          DESCRIPTION));
      MUTATIONS.put(Opcodes.IF_ICMPLT, new Substitution(Opcodes.IF_ICMPLE,
              DESCRIPTION));
    }

    LEMethodVisitor(final MethodMutatorFactory factory,
        final MutationContext context, final MethodVisitor delegateMethodVisitor) {
      super(factory, context, delegateMethodVisitor);
    }

    @Override
    protected Map<Integer, Substitution> getMutations() {
      return MUTATIONS;
    }

  }